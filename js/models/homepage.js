Connection.Homepage = DS.Model.extend({
	userId: DS.attr('number'),
	userName: DS.attr('string'),
	postId: DS.attr('number'),
	timestamp: DS.attr('date'),
	content: DS.attr('string'),
	profilePicPath: DS.attr('string'),
	postFilePath: DS.attr('string'),
	likeCount: DS.attr('number'),
	isLiked: DS.attr('boolean'),
	comments: DS.attr(''),
	isCommentSelected: DS.attr('boolean', {defaultValue: false}),
	Id: function() {
		return this.get('postId')+'Hello';
	}.property('postId'),
	filePath: function() {
		return shortPath(this.get('postFilePath'));
	}.property('postFilePath'),
	isImage: function() {
		return isImage(this.get('postFilePath'));
	}.property('postFilePath'),
	addLikeId: function() {
		return this.get('postId')+"addLike";
	}.property('postId'),
	addCommentId: function() {
		return this.get('postId')+"addComment";
	}.property('postId'),
	likeCountId: function() {
		return this.get('postId')+"likeCount";
	}.property('likeCount'),
});



