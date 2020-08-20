Connection.Userdetail = DS.Model.extend({
	userId: DS.attr('number'),
	userName: DS.attr('string'),
	userProfilePicPath: DS.attr('string'),
	userCoverPicPath: DS.attr('string'),
	userMailId: DS.attr('string'),
	userDOB: DS.attr('string'),
	userWork: DS.attr('string'),
});
