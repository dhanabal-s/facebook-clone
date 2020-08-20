Connection.Myfriends = DS.Model.extend({
	friendId: DS.attr('number'),
	friendName: DS.attr('string'),
	friendCountry: DS.attr('string'),
	friendWork: DS.attr('string'),
	friendProfilePicPath: DS.attr('string')
});