Vue.component('add-recipe-component',{
	data: function () {
	    return {
	    	recipe: {},
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Add Recipe</h1>
			<router-link to="/cookbook">Back to cookbook</router-link>
		    <br />
		    <br />
		    <form v-on:submit="addRecipe">
		        <div class="well">
		            <div class="form-group">
		                <label>Recipe Name</label>
		                <input type="text" class="form-control" placeholder="Recipe Name" v-model="recipe.name">
		            </div>
		            <div class="form-group">
		                <label>Description</label>
		                <input type="text" class="form-control" placeholder="Description" v-model="recipe.description">
		            </div>
		        </div>
		        <div class="well">
		            <div class="form-group">
		                <label>Component</label>
		                
		            </div>
		        </div>
		
		        <button type="submit" class="btn btn-primary">Submit</button>
		    </form>
    	</div>
	`,
    methods : {
    	addRecipe(){
    		
    	}
    	
    },
    created: function(){
    	
    }
});
