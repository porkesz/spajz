var Home = { template: 
	'<home-component></home-component>'
};

var Recipe = { template:
	'<recipe-component></recipe-component>'
};

var routes = [
  { path: '/', component: Home },
  { path: '/recipe/:id', component: Recipe}
];

var router = new VueRouter({
	  mode: 'history',
	  routes: routes
});