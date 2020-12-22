
$(document).ready(function() {

	$('#table1').DataTable({
		columns: [
		   		{data: "firstName", title: "First Name"},
		   		{data: "lastName", title: "Last Name"},
		   		{data: "email", title: "E-Mail"}
		],
		data: {{json(persons)}}
	})

 });