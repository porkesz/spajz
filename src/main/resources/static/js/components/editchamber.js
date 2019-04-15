Vue.component('edit-chamber-component',{
	data: function () {
	    return {
	    	chamber: {},
	    	loading: false
	    }
	},
    template: ` 
    	<div>
			<h1 class="page-header">Edit chamber</h1>
			<router-link to="/chamber">Back to chamber</router-link>
		    <br />
		    <span class="error" style="display:none">All fields are required!</span>
		    <span class="error-nan" style="display:none">Not a number!</span>
		    <br />		        
	        <div v-if="!loading" class="well">
	            <div class="form-group">
	                <label>{{chamber.food.name}} ({{chamber.food.unit}})</label>
	                <input type="text" class="form-control" v-model="chamber.quantity">
	            </div>   
	        </div>
	        <button type="submit" v-on:click="checkEditChamber" class="btn btn-primary">Submit</button>	    
    	</div>
	`,
    methods : {
    	checkEditChamber(){
    		var error = false;
    		$('.error').hide();
    		$('.error-nan').hide();
    		
    		if (this.chamber.quantity == "") {
    			$('.error').show();
    			error = true;
    		}
    		
    		if (isNaN(this.chamber.quantity)) {
    			$('.error-nan').show();
    			error = true;
    		}
    		
    		if (!error) {
    			this.editChamber();
    		}
    	},
    	editChamber(){
    		var self = this;
    		var id = self.$route.params.id;
    		axios({
                method:'put',
                url:'/api/chambers/' + id,
                data: self.chamber
            }).then(function(response){
            	self.$router.push({path: '/chamber'});
            });
    	},
    	fetchChamber(){
    		this.loading = true;
    		var id = this.$route.params.id;
    		axios.get("/api/chambers/" + id)
            .then(function(response){
            	this.chamber = response.data;
            	this.loading = false;
            }.bind(this));
    	}
    },
    created: function(){
    	this.fetchChamber();
    }
});
