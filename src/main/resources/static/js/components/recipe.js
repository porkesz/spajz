Vue.component('recipe-component',{
	data: function () {
	    return {
	    	isInMenu: false,
	    	components: [],
	    	recipe: {
	    	    "id": '',
	    	    "user": {
	    	        "id": '',
	    	        "firstname": "",
	    	        "lastname": "",
	    	        "email": "",
	    	        "username": "",
	    	        "password": "",
	    	        "roles": []
	    	    },
	    	    "name": "",
	    	    "description": ""
	    	},
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Recipe</h1>
			<router-link to="/">Back to recipes</router-link>
		    <br />
		    <br />
		    <ul class="list-group">
	            <li class="list-group-item">
	            	<h4>
	            		{{recipe.name}}
	            		<span v-if="isLoggedIn()" class="pull-right">
	            			<span v-if="isInMenu" class="glyphicon glyphicon-star"></span>
    						<span v-else v-on:click="addMenu" class="glyphicon glyphicon-star-empty"></span>
	            		</span>
	            	</h4>
	            </li>
	            <li class="list-group-item">By {{recipe.user.firstname}} {{recipe.user.lastname}}</li>
	            <li class="list-group-item">{{recipe.description}}</li>
	            <li class="list-group-item">
	            	<span>Components:</span>
	            	<div v-for="component in components">
	            		<span>- {{component.food.name}}: {{component.quantity}} {{component.food.unit}}</span>
	            	</div>
	            </li>
        	</ul>
    	</div>
	`,
    methods : {
    	fetchRecipes(){
    		var id = this.$route.params.id;
    		axios.get("/api/recipes/" + id)
            .then(function(response){
            	this.recipe= response.data;
            }.bind(this))
        },
        fetchComponents(){
        	var id = this.$route.params.id;
        	axios.get("/api/recipeFoodsByRecipeId/" + id)
            .then(function(response){
            	this.components = response.data;
            }.bind(this));
        },
        isLoggedIn(){
        	if (getCookie("login_user")) {
        		return true;
        	}
        	return false;
        },
        setIsInMenu(){
        	if (this.isLoggedIn()) {
	        	var id = this.$route.params.id;
	        	axios.get("/api/menusByUser/" + id)
	            .then(function(response){
	            	this.isInMenu = response.data;
	            }.bind(this));
        	}
        },
        addMenu(){
        	var id = this.$route.params.id;
        	var self = this;
        	axios({
                method:'post',
                url:'/api/menus',
                data: this.recipe
            }).then(function(response){
            	this.setIsInMenu();
            }.bind(this));
        }
    },
    created: function(){
        this.fetchRecipes();
        this.fetchComponents();
        this.setIsInMenu();
    }
});
