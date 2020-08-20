Connection.FindfriendsRoute = Ember.Route.extend({
	model: function() {
		return this.store.findAll("findfriends");
	},

	actions: {
		addFriend : function(friendId) {
			let url = "addfriend?friendid="+friendId;
			let request = new XMLHttpRequest();
			let addFriend = document.getElementById(friendId+'addFriend');
			try {
				request.onreadystatechange = function() {
					if(request.readyState == 4) {
						addFriend.style.display = "none";
					}
				};
				request.open("POST",url,true);
				request.send();
			} catch(err) {
				console.log(err);
			}
		}
	}
});