Connection.Router.map(function() {
  this.route("login", { path: '/' } );
  this.resource("navbar");
  this.resource("findfriends");
  this.resource("homepage");
  this.route('logout');
  this.resource('userdetail', function() {
	this.resource('myfriend');
	this.resource('mypost');
  });
});

