Vue.component('home-component',{
	data: function () {
	    return {
	    	recipes: [],
	    	filterInput: '',
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Recipes</h1>
			<input class="form-control" placeholder="Enter name" v-model="filterInput">
		    <br />
		    <table class="table table-striped">
		        <thead>
		          <tr>
		            <th>Name</th>
		            <th>Author</th>
		            <th></th>
		            <th></th>
		          </tr>
		        </thead>
		        <tbody>
		          <tr v-for="recipe in filterBy(recipes, filterInput)">
		            <td>{{recipe.name}}</td>
		            <td>{{recipe.user.firstname}} {{recipe.user.lastname}}</td>
		            <td></td>
		            <td><router-link class="btn btn-default" v-bind:to="'/recipe/'+recipe.id">View</router-link></td>
		          </tr>
		        </tbody>
		    </table>
    	</div>
	`,
    methods : {
    	fetchRecipes(){
    		axios.get("/api/recipes")
            .then(function(response){
            	this.recipes = response.data;
            }.bind(this))
        },
        filterBy(list, value){
          return list.filter(function(recipe){
            return recipe.name.indexOf(value) > -1;
          });
        }
    },
    created: function(){
        this.fetchRecipes();
    }
});
