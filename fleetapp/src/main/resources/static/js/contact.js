$('document').ready(function() {

	$('.table .btn-primary').on('click',function(event){
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(contact, status){
			$('#txtEmailEdit').val(contact.email);
			$('#txtFirstnameEdit').val(contact.firstname);
			$('#txtIdEdit').val(contact.id);
			$('#txtLastnameEdit').val(contact.lastname);
			$('#txtPhoneEdit').val(contact.phone);
			$('#txtRemarksEdit').val(contact.remarks);
		});
		$('#editModal').modal();
	});

	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();
		var href= $(this).attr('href');
		$.get(href, function(country, status){
			$('#idDetails').val(country.id);
			$('#descriptionDetails').val(country.description);
			$('#codeDetails').val(country.code);
		});
		$('#detailsModal').modal();
	});

	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();
	});
});