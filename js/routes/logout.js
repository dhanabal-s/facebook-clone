Connection.LogoutRoute = Ember.Route.extend({
	model: function() {
		// this.store.unloadAll();
		this.store.find('logout');
	},

	redirect: function() {
		this.transitionTo("login");
	}
});

Connection.Logout = DS.Model.extend({

});