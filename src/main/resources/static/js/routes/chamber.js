var Chamber = { template: 
	'<chamber-component></chamber-component>'
};

var AddChamber = { template:
	'<add-chamber-component></add-chamber-component>'
};

var EditChamber = { template:
	'<edit-chamber-component></edit-chamber-component>'
};

var Menu = { template:
	'<menu-component></menu-component>'	
};

var AddFood = { template:
	'<add-food-component></add-food-component>'
};

var routes = [
  { path: '/chamber', component: Chamber },
  { path: '/add-chamber', component: AddChamber },
  { path: '/edit-chamber/:id', component: EditChamber },
  { path: '/menu', component: Menu },
  { path: '/add-food', component: AddFood}
];

var router = new VueRouter({
	  mode: 'history',
	  routes: routes
});
