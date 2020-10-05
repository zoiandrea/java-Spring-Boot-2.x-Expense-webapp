$(document).ready(function() {
	$('#loginForm').submit(function (event) {
		event.preventDefault();
		if ($('#loginForm')[0].checkValidity() === false) {
			event.stopPropagation();
		} else {
			var formData = $('#loginForm').serializeArray();
			var data = {};
			$(formData).each(function(index, obj) {
				data[obj.name] = obj.value;
			});
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8080/login',
				data: JSON.stringify(data),
				contentType: 'application/json',
				success: function(result) {
					console.log('result===>'+JSON.stringify(result));
					$('#alert').hide();
					localStorage.setItem('loginUser', JSON.stringify(result.loginUser));
					location.href = 'dashboard.html';
				},
				error: function(e) {
					console.log('e===>'+JSON.stringify(e.responseJSON));
					var response = e.responseJSON;
					$('#alert').show();
				}  		
			});
		}
	});
});

$(document).on('keypress',function(e) {
	if(e.which == 13) {
		$("#loginForm").submit();
	}
});
