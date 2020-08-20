Ember.Handlebars.helper('shortPath', function(path) {
  let subPathIndex = path.indexOf("client");
  let length = path.length;
  let subPath = path.substring(subPathIndex, length);
  let finalSubPath = subPath.replace(/\//g, '\\');
  return finalSubPath;
});

Ember.Handlebars.helper('isImage', function(path, options) {
	var ext = getExtension(path);
	  switch (ext.toLowerCase()) {
	    case 'jpg':
	    case 'gif':
	    case 'bmp':
	    case 'png':
	    case 'jpeg':
	      //etc
	    return options.fn(this);
	    // return true;
	  }
	  return options.inverse(this);
});

function getExtension(path) {
  var parts = path.lastIndexOf('.');
  return path.substr(parts+1, path.length);
}

function shortPath(path) {
  let subPathIndex = path.indexOf("client");
  let length = path.length;
  let subPath = path.substring(subPathIndex, length);
  let finalSubPath = subPath.replace(/\//g, '\\');
  return finalSubPath;
}

function oncontrol(ele) {
		ele.controls = true;
}


function offcontrol(ele) {
	ele.controls = false;
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
