function readURL(input) {
	if (input.files && input.files[0]) {
        var reader = new FileReader();
        let tag;
        if(isImage(input.value)) {
          document.getElementById("selectedImage").style.display="block";
          tag = '#selectedImage';
        } else {
          document.getElementById("videoTag").style.display="block";
          tag = '#selectedVideo';
        }
        reader.onload = function (e) {
            $(tag)
                .attr('src', e.target.result)
                .width(100)
                .height(100);
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function oncontrol(ele) {
	ele.controls = true;
}


function offcontrol(ele) {
	ele.controls = false;
}



function isVideo(filename) {
	var ext = getExtension(filename);
	switch (ext.toLowerCase()) {
	case 'm4v':
	case 'avi':
	case 'mpg':
	case 'mp4':
	case 'ogg':
	case 'webm':
		return true;
	}
	return false;
}


function isImage(filename) {
	var ext = getExtension(filename);
    switch (ext.toLowerCase()) {
    	case 'jpg':
    	case 'gif':
    	case 'bmp':
    	case 'png':
    	case 'jpeg':
      		return true;
  	}
  	return false;
}


function getExtension(filename) {
	var parts = filename.split('.');
	return parts[parts.length - 1];
}


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