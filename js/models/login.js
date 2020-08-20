Connection.Login = Ember.Model.extend({
	userName = DS.attr('string'),
	userId = DS.attr('number'),
	userProfilePicPath = DS.attr('string'),
	isMatch = DS.attr('boolean')
});