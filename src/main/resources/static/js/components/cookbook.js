Vue.component('cookbook-component',{
	data: function () {
	    return {
	    	myRecipes: [],
	    	loading: false
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Cookbook</h1>
			<router-link to="/add-recipe">Add new recipe</router-link>
		    <br />
		    <br />
		    <div v-if="!loading">
		    <div v-for="recipe in myRecipes">
		    	<ul class="list-group">
	            <li class="list-group-item">
	            	<h4>
	            		{{recipe.recipe.name}}
	            		<span class="pull-right">
	            			<router-link v-bind:to="'/edit-recipe/'+recipe.recipe.id"><input class="button-primary" type="submit" value="Edit" /></router-link>
	            			<input class="button-primary" type="submit" value="Delete" />
	            		</span>
	            	</h4>
	           	</li>
	            <li class="list-group-item">{{recipe.recipe.description}}</li>
	            <li class="list-group-item">
	            	<span>Components:</span>
	            	<div v-for="component in recipe.recipeFoods">
	            		<span>- {{component.food.name}}: {{component.quantity}} {{component.food.unit}}</span>
	            	</div>
	            </li>
        	</ul>
		    </div>
    	</div>
    	</div>
	`,
    methods : {
    	fetchMyRecipes(){
    		this.loading = true;
    		username = getCookie("login_user");
    		if(username){
    			axios.get("/api/recipes")
                .then(function(response){
                	 var recipes = response.data;
                	 if(recipes){
                		 var temp = [];
                		 $.each(recipes, function( index, recipe ) {
                			 if (username == recipe.user.username) { 
                				 axios.get("/api/recipeWithRecipeFood/" + recipe.id)
                	                .then(function(response){
                	                	 temp.push(response.data);
                	                }.bind(this)) 
                			 }
                		 });
                		 this.myRecipes = temp;
                		 this.loading = false;
                	 }
                	 
                }.bind(this))
    		}
        }
    },
    created: function(){
    	this.fetchMyRecipes();
    }
});
