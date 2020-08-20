Ember.Handlebars.helper('duration-date-format', function(date) {
  return moment(date).fromNow();
});
