Connection.Fetchcomment = DS.Model.extend({
	// attrs: {
	//     post: { key: "postId" }
	// },
	postId: DS.belongsTo('homepage'),
	// post: DS.belongsTo('mypost'),
	postPublisherId: DS.attr('number'),
	commentPublisherId: DS.attr('number'),
	commentId: DS.attr('number'),
	likeCount: DS.attr('number'),
	commentPublisherName: DS.attr('string'),
	commentContent: DS.attr('string'),
	commentPublisherPicPath: DS.attr('string'),
	commentTime: DS.attr('date'),
	commentePubPicPath: function() {
		if (this.get('commentPublisherPicPath')) {
			return shortPath(this.get('commentPublisherPicPath'));
		}
		return null;
	}.property('commentPublisherPicPath')
});