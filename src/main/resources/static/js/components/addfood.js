Vue.component('add-food-component',{
	data: function () {
	    return {
	    	value: null,
	    	options: [],
	    	food: {},
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Add food</h1>
			<router-link to="/chamber">Back to chamber</router-link>
		    <br />
		    <span class="error" style="display:none">All fields are required!</span>
		    <br />	
		    	<div class="well">
		            <div class="form-group">
		                <label>Food name</label>
		                <input type="text" class="form-control" placeholder="Food name" v-model="food.name">
		            </div>
		            <div class="form-group">
		                <label>Unit(g, dkg, kg, dl, l, ...)</label>
		                <input type="text" class="form-control" placeholder="Unit" v-model="food.unit">
		            </div>
		            <div class="form-group">
		                <label>Calorie (cal/100g)</label>
		                <input type="text" class="form-control" placeholder="Calorie" v-model="food.calorie">
		            </div>
		        </div>
		        <div class="well">
		            <div class="form-group">
		                <label>Food category</label>
    					<vue-multiselect 
    						v-model="value" 
    						:options="options" 
    						:multiple="false"
    						placeholder="Pick some" 
    						label="name" 
    						track-by="name" 
    						:preselect-first="true">
    							<template 
    								slot="selection" 
    								slot-scope="{ values, search, isOpen }">
    									<span class="multiselect__single" v-if="values.length &amp;&amp; !isOpen">
    										{{ values.length }} options selected
    									</span>
    							</template>
    					</vue-multiselect>
		            </div>	    
		    	</div>       
		    <button type="submit" v-on:click="checkAddFood" class="btn btn-primary">Submit</button>	    
    	</div>
	`,
    methods : {
    	checkAddFood(){
    		var error = true;
    		if ( this.food.name != undefined
        			&& this.food.unit != undefined
        			&& this.food.calorie != undefined
        			&& this.value != null) {
    				error = false;
        			this.addFood();
        		}
    		
    		if (error) {
    			$('.error').show();
    		}
    	},
    	addFood(){
    		this.food.foodCategory = this.value;
    		this.food.calorie = parseInt(this.food.calorie);
    		var self = this;
    		axios({
                method:'post',
                url:'/api/foods',
                data: self.food
            }).then(function(response){
            	self.$router.push({path: '/chamber'});
            });
    	},
    	fetchFoodCategory(){
    		axios.get("/api/foodCategories")
            .then(function(response){
            	this.options = response.data;
            }.bind(this));
    	}
    },
    created: function(){
    	this.fetchFoodCategory();
    }
});
