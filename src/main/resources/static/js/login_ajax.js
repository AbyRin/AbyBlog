$(function() {

	$(".sign_div").submit(function() {
		const email = $("input[name = 'email']").val();
		const password = $("input[name = 'password']").val();

		$.post("/index/doLogin", {
			"email" : email,
			"password" : password
		}, function(result) {
			$("#form_msg").html(result);
			$("#form_msg").css("color", "red");
			window.location.href="/index";
		});

		return false;
	})
})