Connection.Findfriends = DS.Model.extend({
	friendId: DS.attr('number'),
	friendName: DS.attr('string'),
	friendCountry: DS.attr('string'),
	friendWork: DS.attr('string'),
	friendProfilePicPath: DS.attr('string'),
	friendProfilePath: function() {
		return shortPath(this.get('friendProfilePicPath'));
	}.property('friendProfilePicPath'),
	addFriendId: function() {
		return this.get('friendId')+'addFriend';
	}.property('friendId')
});