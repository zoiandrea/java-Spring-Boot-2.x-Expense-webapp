$(document).ready(function() {

	var loginUser = JSON.parse(localStorage.getItem('loginUser'));
	if(!loginUser) { location.href = 'http://localhost:8080'; }
	$('#loginUser').html(loginUser.username);

	var table = $('#projectList').DataTable({
    	ajax: {
    		type: 'GET',
    		url: 'http://localhost:8080/project/findall',
    		dataType: 'json',
    		contentType: 'application/json',
    		error: function (xhr, error, thrown) {
					console.log('error===>'+JSON.stringify(xhr.responseJSON));
        }
    	},
      columns: [
				{ data: null,
    	    render: function (data, type, row, meta) {
          	return meta.row + 1;
          }
    	  },
        { data: "title" },
        { data: "expense",
					render: function (data, type, row) {
						return '&#36;&nbsp;'+data;
					}
				},
        { data: "duration",
					render: function (data, type, row) {
						return data+'&nbsp;Days';
					}
				},
				{ data: "createdby" },
				{ data: "creation" }
      ],
      columnDefs: [
        { targets: 6,
          data: null,
          orderable: false,
          width: '8%',
          render: function (data, type, row) {
          	return '<button type="button" class="btn btn-info btn-xs">View</button>'
          }
        },
				{ targets: 7,
          data: null,
          orderable: false,
          width: '8%',
          render: function (data, type, row) {
          	return '<button type="button" class="btn btn-secondary btn-xs">Edit</button>'
          }
        },
				{ targets: 8,
          data: null,
          orderable: false,
          width: '8%',
          render: function (data, type, row) {
          	return '<button type="button" class="btn btn-danger btn-xs">Delete</button>'
          }
        }
      ],
			dom: "<'row'<'col-sm-12 col-md-5'l><'col-sm-12 col-md-4'<'toolbar'>><'col-sm-12 col-md-3 d-flex justify-content-end'f>>" +
					 "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>"
	});

	$(".toolbar").html("<button type='button' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#projectModal'>Add Project</button>");

	$('#projectForm').submit(function (event) {
		event.preventDefault();
		if ($('#projectForm')[0].checkValidity() === false) {
			event.stopPropagation();
		} else {
			var formData = $('#projectForm').serializeArray();
			var data = {};
			$(formData).each(function(index, obj) {
				data[obj.name] = obj.value;
			});
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8080/project/save/'+loginUser.username,
				data: JSON.stringify(data),
				cache: false,
				contentType: 'application/json',
				success: function(result) {
					$("#alertModalBody").html('<strong class="text-success">'+result+'</strong>');
					$("#alertModal").modal("show");
					console.log('data.id===>'+data.id);
					if(data.id) { } else { $("#projectForm")[0].reset(); }
				},
				error: function(e) {
					console.log('e===>'+JSON.stringify(e));
					var response = e.responseJSON;
					$("#alertModalBody").html('<strong class="text-danger">'+response.error+'</strong>');
					$("#alertModal").modal("show");
				}
			})
		}
	});

	$('#projectModal').on('hidden.bs.modal', function (e) {
  	location.reload();
	});

	$('#projectList tbody').on('click', 'button.btn-info', function() {
		var data = table.row($(this).parents('tr')).data();
		loadProjectModalView(data);
		$("#projectModalView").modal("show");
	});

	$('#projectList tbody').on('click', 'button.btn-secondary', function() {
		var data = table.row($(this).parents('tr')).data();
		loadProjectModal(data);
		$("#projectModal").modal("show");
	});

	$('#projectList tbody').on('click', 'button.btn-danger', function() {
		var data = table.row($(this).parents('tr')).data();
		if (confirm("Are you sure you want to delete this record?")){
			$.ajax({
				type: 'DELETE',
				url: 'http://localhost:8080/project/delete/'+data.id,
				success: function(response) {
					location.reload();
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

});

function logout() {
	localStorage.removeItem('loginUser');
	location.href = 'http://localhost:8080';
}

function loadProjectModal(data) {
	$('#proId').val((data.id != null) ? data.id : '');
	$('#title').val((data.title != null) ? data.title : '');
	$('#description').val((data.description != null) ? data.description : '');
	$('#expense').val((data.expense != null) ? data.expense : '');
	$('#duration').val((data.duration != null) ? data.duration : '');
}

function loadProjectModalView(data) {
	$('#titleValue').text((data.title != null) ? data.title : '');
	$('#descriptionValue').text((data.description != null) ? data.description : '');
	$('#expenseValue').text((data.expense != null) ? '$ '+data.expense : '');
	$('#durationValue').text((data.duration != null) ? data.duration+' Days' : '');
	$('#createdbyValue').text((data.createdby != null) ? data.createdby : '');
	$('#creationValue').text((data.creation != null) ? data.creation : '');
}
