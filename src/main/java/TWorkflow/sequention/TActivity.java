/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package TWorkflow.sequention;

/**
 *
 * @author mrbis
 */
public class TActivity implements Comparable<TActivity>{
     // Порядковый индекс в последовательности процесса
        private Integer index;
        
        //Шаг к которому пренадлежит данная активность 
        private TStep tstep;
        
        //Наименование
        private String title;

        //Состояние активности
        private Boolean finished = false;


        /**
         *
     * @param tstep */
        public TActivity(TStep tstep) {
            this.tstep = tstep;
            this.index = this.tstep.getTActivityList().size() + 1;
        }

        /**
         * Порядковый индекс в последовательности процесса
         *
         * @return (Integer)
         */
        public Integer getIndex() {
            return index;
        }

        /**
         * Шаг к которому пренадлежит данная активность 
         *
         * @return (TProcess)
         */
        public TStep getTstep() {
            return tstep;
        }

        /**
         * Возвращает наименование
         *
         * @return (String)
         */
        public String getTitle() {
            return title;
        }

        /**
         * Устанавливает наименование
         *
         * @param title (String)
         */
        public void setTitle(String title) {
            this.title = title;
        }
        
        /**
         * Возвращает состояние активности
         * true - Завершон
         * false - Не завершон
         *
         * @return (Boolean)
         */
        public Boolean getFinished() {
            return finished;
        }

        /**
         * Устанавливает состояние активности
         * true - Завершон
         * false - Не завершон
         *
         * @param finished (Boolean)
         */
        public void setFinished(Boolean finished) {
            this.finished = finished;
        }
        
        /**
         * Меняет активность местами с последующим
         * 
         */
        public void moveToNext(){
            
            if(this.tstep.getTActivityList().size() > this.index){
                TActivity nextActivity = this.tstep.getTActivityList().get(this.index);
                this.index++;
                nextActivity.index--;
            }
            
        }
        
        /**
         * Меняет активность местами с предидущим
         * 
         */
        public void moveToPrevious(){
            if(this.index > 1){
                TActivity nextActivity = this.tstep.getTActivityList().get(this.index - 2);
                this.index--;
                nextActivity.index++;
            }
        }

        @Override
        public int compareTo(TActivity o) {
            return this.index.compareTo(o.index);
        }
    
}
