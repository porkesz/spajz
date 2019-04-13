Vue.component('edit-recipe-component',{
	data: function () {
	    return {
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
			<h1 class="page-header">Edit Recipe</h1>
			<router-link to="/cookbook">Back to cookbook</router-link>
		    <br />
		    <br />
		    <ul class="list-group">
	            <li class="list-group-item"><h4>{{recipe.name}}</h4></li>
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
        }
    },
    created: function(){
        this.fetchRecipes();
        this.fetchComponents();
    }
});
