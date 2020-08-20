Connection.MypostRoute = Ember.Route.extend({
	model: function() {
		return this.store.findAll("mypost");
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


var likeChange = function(postId) {
	let url = "like?postId="+postId;
	let likeBtn = document.getElementById(postId+'addLike');
	let likeCount = document.getElementById(postId+'likeCount');
	let request = new XMLHttpRequest();
	try {
		request.onreadystatechange = function() {
			if(request.readyState == 4) {
				let response = JSON.parse(request.responseText);
				if(response){
					likeCount.innerHTML = response.totalLike;
					if(response.isLiked) {
						likeBtn.style.backgroundColor = "#4267b2";
					} else {
						likeBtn.style.backgroundColor = "#e9ebee";
					}
				}
			}
		};

		request.open("POST", url, true);
		request.send();
	} catch(err) {
		console.log(err);
	}
}


var comment = function(postId, postPubId, comtParId) {
	let comment = document.getElementById(postId+"addComment");
	let commentcontent = comment.value;
	let url = "addcomment?postId="+postId+"&postPublisherId="+postPubId+"&commentParentId="+comtParId+"&commentcontent="+commentcontent;
	let request = new XMLHttpRequest();
	try {
		request.onreadystatechange = function() {
			if(request.readyState == 4) {
				comment.value = "";
			}
		};
		request.open("POST", url, true);
		request.send();
	} catch(err) {
		console.log(err); 
	}
}


var newPostAdding = function() {
	let textPost = document.getElementById("newPost");
	let url = "createpost?postContent="+textPost.value;
	let request = new XMLHttpRequest();
	let form = document.getElementById('newPostForm');
	let data = new FormData(form);
	request.onload = function() {
		textPost.value = "";
		let mediaPost = document.getElementById("file-upload");
		mediaPost.value = "";
		let img = document.getElementById("selectedImage");
		img.src = "";
		img.style.display = "none";
		let video = document.getElementById("videoTag");
		video.style.display = "none";
		document.getElementById("selectedVideo").src = "";
	}

	request.open("POST", url, true);
	request.send(data);
}