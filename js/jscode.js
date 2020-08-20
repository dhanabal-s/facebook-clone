function readURL(input) {
	if (input.files && input.files[0]) {
        var reader = new FileReader();
        document.getElementById("selectimage").style.display="block";
        reader.onload = function (e) {
            $('#selectimage')
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


function offcontro(ele) {
	ele.controls = false;
}



function isVideo(filename) {
  console.log(7);
  var ext = getExtension(filename);
  switch (ext.toLowerCase()) {
    case 'm4v':
    case 'avi':
    case 'mpg':
    case 'mp4':
    case 'ogg':
    case 'webm':
      // etc
      console.log("true");
      return true;
  }
  console.log("t");
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
      //etc
      return true;
  }
  return false;
}


function getExtension(filename) {
  var parts = filename.split('.');
  return parts[parts.length - 1];
}


function addLike(postId) {
  let url = "like?postId="+postId;
  var req = new XMLHttpRequest();

  try {

    req.onreadystatechange = function () {
      if(req.readyState == 4) {
        let obj = JSON.parse(req.responseText);
        let prevTotal = document.getElementById(postId+"likeCount");
        let singOrPlur = document.getElementById(postId+"likeCountName");
        let newTotal = prevTotal.innerHTML-(-obj.likeCount);
        prevTotal.innerHTML = newTotal;
        let likeBack = document.getElementById(postId);

        if(newTotal > 1) {
          singOrPlur.innerHTML = "Likes";
        } else {
          singOrPlur.innerHTML = "Like";
        }

        if(obj.likeCount == -1) {
            likeBack.style.backgroundColor= "#e9ebee";
        } else {
            likeBack.style.backgroundColor="lightgreen";
        }
      }
    };

    req.open("POST",url,true);
    req.send();
  } catch(err) {
    console.log(err);
  }
}


function addComment(postId, postPubId, comtParId = 0) {

  let comment = document.getElementById(postId+"comment");
  let commentcontent = comment.value;
  let url = "addcomment?postId="+postId+"&postPublisherId="+postPubId+"&commentParentId="+comtParId+"&commentcontent="+commentcontent;
  let req = new XMLHttpRequest();

  try {

      req.onreadystatechange = function() {
      if(req.readyState == 4) {
        comment.value = "";
      }
    };

    req.open("POST",url,true);
    req.send();
  } catch(err) {
    console.log(err);
  }
}


function addFriend(friendId) {

  let url = "addfriend?friendid="+friendId;
  let req = new XMLHttpRequest();
  let ele = document.getElementById(friendId+"show");

  try {

    req.onreadystatechange = function() {
      if(req.readyState == 4) {
        ele.style.display = "none";
      }  
    };
    req.open("POST",url,true);
    req.send();

  } catch(err) {
    console.log(err);
  }

}