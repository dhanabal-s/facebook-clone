Connection.UserdetailRoute = Ember.Route.extend({
	model: function() {
		return this.store.find("userdetail");
	},

	setupController: function(controller, model) {
		controller.set('model', model);
	},

});
