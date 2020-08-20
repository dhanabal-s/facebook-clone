window.Connection = Ember.Application.create();

Connection.ApplicationAdapter = DS.FixtureAdapter.extend();

Connection.ApplicationAdapter = DS.RESTAdapter.extend({
  namespace: 'connection',

  pathForType: function(type) {
  	return Ember.String.singularize(type);
  },
  
  defaultSerializer: '-default'
});