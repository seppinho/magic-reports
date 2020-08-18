package lukfor.magic.reports;

import java.util.List;
import java.util.Vector;
import java.util.function.Function;

import lukfor.magic.reports.functions.IncludeScriptFunction;
import lukfor.magic.reports.functions.IncludeStyleFunction;
import lukfor.magic.reports.widgets.IWidget;
import lukfor.magic.reports.widgets.WidgetFactory;

public class MagicReport extends HtmlReport {
	private List<IWidget> importedWidgets = new Vector<>();

	public MagicReport(String inputDirectory) {

		super(inputDirectory);

		final IncludeStyleFunction styleFunction = new IncludeStyleFunction(this);

		set("import_widget", new Function<String, String>() {

			@Override
			public String apply(String id) {

				IWidget widget = WidgetFactory.createWidget(id, MagicReport.this);
				importedWidgets.add(widget);

				// register render function
				set(widget.getId(), widget.getRenderFunction());

				// build head tags
				String html = "<!-- Widget: " + id + " -->\n";
				for (String style : widget.getStyles()) {
					html += styleFunction.apply(style) + "\n";
				}
				return html;
			}

		});

		final IncludeScriptFunction scriptFunction = new IncludeScriptFunction(this);

		set("activate_widgets", new Function<String, String>() {

			@Override
			public String apply(String arg0) {

				// activate and init only imported widgets
				String html = "";

				for (IWidget widget : importedWidgets) {
					html += "\n";
					html += "<!-- Widget: " + widget.getId() + " -->\n";
					for (String script : widget.getScripts()) {
						html += scriptFunction.apply(script) + "\n";
					}
				}

				html += "\n";
				html += "<!-- Init Widgets -->\n";
				html += "<script>\n";
				html += "$(document).ready( function () {\n";
				for (IWidget widget : importedWidgets) {
					html += "\n";
					html += "// Init " + widget.getId() + "\n";
					html += widget.getInitializer();
				}
				html += "});\n";
				html += "</script>";

				return html;

			}

		});

	}

}