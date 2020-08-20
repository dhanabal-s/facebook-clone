Connection.HomepageRoute = Ember.Route.extend({
	model: function() {
		return this.store.findAll("homepage");
	},

	setupController: function(controller, model) {
		this.controller.set('model', model);
	},

	actions: {
		addOrRemoveLike : function(postId) {
			likeChange(postId);
		},

		addComment: function(postId, postPubId, comtParId = 0) {
			comment(postId, postPubId, comtParId);
		},

		addNewPost: function() {
			newPostAdding();
		},

		fetchComments: function(postId, userId, curPost) {
			let self = this;
			fetchCommentsPromise(postId, userId).then(function(response) {
				curPost.set('isCommentSelected', true);
				curPost.set('comments', response);
			}).catch(function(err) {
				console.log(err);
			});
		}
	}
});

var fetchCommentsPromise = function(postId, userId) {
	return new Promise(function(resolve, reject) {
		let url = "fetchcomment?postid="+postId+"&userid="+userId;
		let request = new XMLHttpRequest();
		var response;

		try {
			request.onreadystatechange = function() {
				if(request.readyState == 4) {
					response = JSON.parse(request.responseText);
					resolve(response);
				}
			};

			request.open("POST", url, true);
			request.send();
		} catch(err) {
			reject(err);
		}
	});
}
