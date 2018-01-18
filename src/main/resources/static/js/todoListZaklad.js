var staticTasks='[{"id":"1","task":"Write Europass CV as web page","isDone":true},{"id":"2","task":"Implement TODO list","isDone":false},{"id":"3","task":"Do final assignment","isDone":false}]';

var tasks=JSON.parse(staticTasks);

var taskTemplate =$("#tmplTask").html();

function addTaskToHtml(task){
    if(task){
        $element=$(Mustache.render(taskTemplate,task));

        $("#frmTasks").append($element);

        if(task.isDone){
            $element.removeClass("activeTask");
            $element.addClass("completedTask");
        }

        
      //  $element=$(htmlCode);
        
        $element.click(
            function(){
                $(this).toggleClass("activeTask");
                $(this).toggleClass("completedTask");
                for(var i=0, len=tasks.length; i<len; i++){
                    if($(this).attr('data-id') == tasks[i].id){
                        tasks[i].isDone = !tasks[i].isDone;
                    }
                }
            }
      
           
        
        
        );
    }
}

//--------------------------------------


//1.nacitanie uloh
for(var i=0, len=tasks.length; i<len; i++){
    addTaskToHtml(tasks[i]);
}
/*
//Alternatíva predch. cyklu pre EcmaScript 6
for(var task of tasks){
     addTaskToHtml(task);
}
*/


//$("#btAddTask").click(
//		function(){
//			console.log("Klikam sem a tam");
//		}
//);


//$("#btAddTask").click(
//		function(){
//	console.log($("#inNewTask").val().trim());
//});



$("#btAddTask").click(
		function(){
	
	
	$newTaskInput = $("#inNewTask");
			
	var text = $("#inNewTask").val().trim();
	
	if(text == "") return;
	
	var newTask = {
			//id:tasks.length+1,
			id:Date.now(),
			task:text,
			idDone:false
	};
	
	tasks.push(newTask);
	addTaskToHtml(newTask);

	
	$newTaskInput.val("");
	
	//	console.log(newTask);
		
		});


$("#btRemCmpl").click(
function(){
//console.log($("#inNewTask").val().trim());
//console.log("Klikam sem a tam");
	
	
	
	//funkcia na odoberanie taskov
	/*for(var i=0; i<tasks.length; i++){
	   
		 // if($(this).attr('data-id') == tasks[i].id){
              //tasks[i].isDone = !tasks[i].isDone;
              if (tasks[i].isDone == true) {
            	  //console.log("task mam done");
            	    tasks.splice(i,1);
              		i--;
              		}
	}
*/
	//console.log("task mam done");

	
	tasks = tasks.filter(
			function(task){
		return !task.isDone;
	}
	);
	
	$("#frmTasks").html("");
	for(var i=0, len=tasks.length; i<len; i++){
	    addTaskToHtml(tasks[i]);
	}
	
}
	
	//}
		
);









