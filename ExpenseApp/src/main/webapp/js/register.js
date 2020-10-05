$(document).ready(function() {
	$('#registrationForm').submit(function (event) {
		event.preventDefault();
		if ($('#registrationForm')[0].checkValidity() === false) {
			event.stopPropagation();
		} else {
			var formData = $('#registrationForm').serializeArray();
			var data = {};
			$(formData).each(function(index, obj) {
				data[obj.name] = obj.value;
			});
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8080/register',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function(result) {
					console.log('result===>'+JSON.stringify(result));
          $("#alertModalBody").html('<strong class="text-success">'+result+'</strong>');
					$("#alertModal").modal("show");
					$("#registrationForm")[0].reset();
				},
				error: function(e) {
          console.log('e===>'+JSON.stringify(e));
					var response = e.responseJSON;
					$("#alertModalBody").html('<strong class="text-danger">'+response.error+'</strong>');
					$("#alertModal").modal("show");
				}
			});
		}
	});
  $('#alertModal').on('hidden.bs.modal', function (e) {
  	location.href = "http://localhost:8080";
	});
});

$(document).on('keypress',function(e) {
	if(e.which == 13) {
		$("#registrationForm").submit();
	}
});
