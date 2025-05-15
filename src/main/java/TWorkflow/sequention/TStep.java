/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package TWorkflow.sequention;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mrbis
 */
public class TStep implements Comparable<TStep>{

    //порядковый индекс в последовательности процесса
        private Integer index;
        
        //Процесс к которому пренадлежит данный шаг 
        private TProcess tprocess;

        //Наименование
        private String title;

        //Тип шака (паралельный / последовательный)
        private Boolean parelel = false;

        //Тип контроля исполнения шака (Все действия / Хотябы одно из действий)
        private Boolean atLeastOne = false;

        //Список Активностей
        private List<TActivity> tactivityList;

        /**
         *
     * @param tprocess */
        public TStep(TProcess tprocess) {
            this.tactivityList = new ArrayList<>();
            this.tprocess = tprocess;
            this.index = this.tprocess.getTstepList().size() + 1;

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
         * Процесс к которому пренадлежит данный шаг 
         *
         * @return (TProcess)
         */
        public TProcess getTprocess() {
            return tprocess;
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
         * Возвращает тип шага true - паралельный (Все активности могут
         * выполняться одновременно и в произвольном парядке) false -
         * последовательный (Все активности будут выполняться строго
         * последовательно)
         *
         * @return (Boolean)
         */
        public Boolean getParelel() {
            return parelel;
        }

        /**
         * Устанавливает тип шага true - паралельный (Все активности могут
         * выполняться одновременно и в произвольном парядке) false -
         * последовательный (Все активности будут выполняться строго
         * последовательно)
         *
         * @param parelel (Boolean)
         */
        public void setParelel(Boolean parelel) {
            this.parelel = parelel;
        }

        /**
         * Возвращает тип контроля исполнения шака true - Хотябы одна (шаг
         * счетаеться исполненым если хотябы у одной активности состояние
         * исполнено (в состояние 'finished - true' ) ) false - Все (шаг
         * счетаеться исполненым если у всех активностей должно быть исполнены
         * (в состояние 'finished - true' ) )
         *
         * @return (Boolean)
         */
        public Boolean getAtLeastOne() {
            return atLeastOne;
        }

        /**
         * Устанавливает тип контроля исполнения шака true - Хотябы одна (шаг
         * счетаеться исполненым если хотябы у одной активности состояние
         * исполнено (в состояние 'finished - true' ) ) false - Все (шаг
         * счетаеться исполненым если у всех активностей должно быть исполнены
         * (в состояние 'finished - true' ) )
         *
         * @param atLeastOne (Boolean)
         */
        public void setAtLeastOne(Boolean atLeastOne) {
            this.atLeastOne = atLeastOne;
            if (atLeastOne) {
                this.parelel = true;
            }
        }

        /**
         * Возвращает список активностей в шаге
         *
         * @return List
         */
        public List<TActivity> getTActivityList() {
            Collections.sort(this.tactivityList);
            return tactivityList;
        }

        /**
         * Устанавливает список активностей в шаге
         *
         * @param tactivityList List
         */
        public void setTActivityList(List<TActivity> tactivityList) {
            this.tactivityList = tactivityList;
        }

        /**
         * Возвращает состояние шага
         * true - завершон
         * false - не завершон
         *
         * @return (Boolean)
         */
        public Boolean getFinished() {
            Boolean result = false;
            Collections.sort(this.tactivityList);
            List<TActivity> finishedTActivity = this.tactivityList.stream().filter(a -> a.getFinished() == true).collect(Collectors.toList());
            if (this.atLeastOne && finishedTActivity.size() >= 1) {
                result = true;
            } else if (!this.atLeastOne && finishedTActivity.size() == tactivityList.size()) {
                result = true;
            }
            return result;
        }

        /**
         * Возвращает список активностей следующих для исполнения в текущем шаге
         * @return List
         */
        public List<TActivity> getNextTActivityList() {
            List<TActivity> result = new ArrayList<>();
            Collections.sort(this.tactivityList);
            List<TActivity> finishedTActivity = tactivityList.stream().filter(a -> a.getFinished() == false).collect(Collectors.toList());
            if (this.parelel) {
                result.addAll(finishedTActivity);
            } else {
                if (!finishedTActivity.isEmpty()) {
                    result.add(finishedTActivity.get(0));
                }
            }
            return result;
        }
        
        
        /**
         * Меняет шаг местами с последующим
         * 
         */
        public void moveToNext(){
            if(this.tprocess.getTstepList().size() > this.index){
                TStep nextStep = this.tprocess.getTstepList().get(this.index);
                this.index++;
                nextStep.index--;
            }
            
        }
        
        /**
         * Меняет шаг местами с предидущим
         * 
         */
        public void moveToPrevious(){
            if(index > 1){
                TStep preStep = this.tprocess.getTstepList().get(this.index - 2);
                this.index--;
                preStep.index++;
            }
        }

        @Override
        public int compareTo(TStep o) {
            return this.index.compareTo(o.index);
        }
    
}
