package Code.Model;

import java.time.LocalDate;

    public class Task {
        private String taskName;
        private String category;
        private int priorityLevel;
        private String deadline;
        private int id;

        //Default constructor
        public Task(){
        }

        /**
         * Methode that allow to modify the name of the task
         * @param name : name of the task
         */
        //Getters and setters
        public void setName(String name) {
            taskName = name;
        }

        /**
         * method that allow to get the Task Name attribute
         * @return this
         */
        public String getName() {
            return taskName;
        }

        /**
         * Method that allow to modify the category of the task
         * @param category : the category name of the task
         */
        public void setCategory(String category) {
            this.category = category;
        }

        /**
         * method that to get the Task category attribute
         * @return this
         */
        public String getCategory() {
            return category;
        }

        /**
         * Method that allow to modify the category of the task
         * @param deadline : the deadline of the task
         */
        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        /**
         * method that allow to get the Task deadline attribute
         * @return this
         */
        public String getDeadline(){
            return deadline;
        }

        /**
         * Method that allow to modify the category of the task
         * @param id : represent the number of the current task
         */
        public void setId(int id) {
            this.id= id;
        }

        /**
         * method that allow to get the Task ID attribute
         * @return this
         */
        public int getId() {
            return id;
        }


        /**
         * convert a String Deadline to LocalDate type
         * @return LocalDate
         */
        public LocalDate getDeadlineDate()
        {
            return Tasks.convertStringToDate(this.deadline);
        }

        public int getPriorityLevel() {
            return priorityLevel;
        }


        /**
         * @param level : level of the priority
         * @throws IllegalArgumentException : incorrect level of priority
         */
        public void setPriorityLevel(int level) {
            //vérifier que le lever est compris entre 0 et 7
            if(level>0&&level<8){
                this.priorityLevel = level;
            }
            else{
                throw new IllegalArgumentException("level must be greater than 0 and less than 8");
            }

        }

        /**
         *Constructor of a task
         * @param name : setup the name of a task
         * @param category : setup the category of a task
         * @param priorityLevel :setup the priorityLevel of a task
         */
        public Task(String name, String category, int priorityLevel, String deadline){
            this.taskName = name;
            this.category = category;
            this.priorityLevel = priorityLevel;
            this.deadline = deadline;
        }

    }

