var Cookbook = { template: 
	'<cookbook-component></cookbook-component>'
};

var EditRecipe = { template:
	'<edit-recipe-component></edit-recipe-component>'
};

var AddRecipe = { template:
	'<add-recipe-component></add-recipe-component>'
};

var routes = [
  { path: '/cookbook', component: Cookbook },
  { path: '/edit-recipe/:id', component: EditRecipe},
  { path: '/add-recipe', component: AddRecipe}
];

var router = new VueRouter({
	  mode: 'history',
	  routes: routes
});