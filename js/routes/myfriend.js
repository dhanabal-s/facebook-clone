Connection.MyfriendRoute = Ember.Route.extend({
	model: function() {
		return this.store.findAll('myfriends');
	}
});
