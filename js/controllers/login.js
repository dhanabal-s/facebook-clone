Connection.LoginController = Ember.ObjectController.extend({
	actions: {
		login: function() {
			var self = this;
			let email = document.getElementById('loginemail').value;
			let password = document.getElementById('loginpassword').value;
			loginPromise(email, password).then(function(response) {
				if(response.message == "match") {
					self.transitionTo("homepage");
				} else {
					alert("mismatch");
				}
			}).catch( function(err) {
				console.log(err);
			});
		},

		signup: function() {
			var self = this;
			let email = document.getElementById('signup-mail').value;
			let password = document.getElementById('signup-password').value;
			let confirmPassword = document.getElementById('signup-conPassword').value;
			let name = document.getElementById('signup-name').value;
			if(password == confirmPassword) {
				signupPromise(name, email, password).then( function(response){
					if(response.message == 'success') {
						self.transitionTo("homepage");
					} else {
						alert(response.message);
					}
				}).catch( function(response){
					console.log(response);
				});
			} else {
				alert("Password and Confirm Password Mismatch");
			}
		}
	},

});

function loginPromise(email, password) {
	return new Promise(function(resolve, reject) {
		let url = "login?loginemail="+email+"&loginpassword="+password;
		let request = new XMLHttpRequest();
		var response;
		try {
			request.onreadystatechange = function() {
				if(request.readyState == 4) {
					response = JSON.parse(request.responseText);
					resolve(response);
				}
			}

			request.open("POST", url, true);
			request.send();
		} catch(err) {
			console.log(err);
		}
	});
}


function signupPromise(name, email, password) {
	return new Promise( function(resolve, reject) {
		let url = "signup?email="+email+"&name="+name+"&password="+password;
		let request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			if(request.readyState == 4) {
				response = JSON.parse(request.responseText);
				resolve(response);
			}
		}

		request.open("POST", url, true);
		request.send();
	});
}