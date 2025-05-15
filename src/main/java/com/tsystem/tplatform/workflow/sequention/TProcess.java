package com.tsystem.tplatform.workflow.sequention;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Прорцесс
 *
 * @author Bishop
 */
public class TProcess {

    //Наименование
    private String title;

    //Список шагов в процессе (шаги выполняються последовательно)
    private List<TStep> tstepList;

    /**
     *
     */
    public TProcess() {
        this.tstepList = new ArrayList<>();
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
     * Возвращает список шаков
     *
     * @return (List)
     */
    public List<TStep> getTstepList() {
        Collections.sort(this.tstepList);
        return tstepList;
    }

    /**
     * Устанавливает список шаков
     *
     * @param tstepList (List)
     */
    public void setTstepList(List<TStep> tstepList) {
        this.tstepList = tstepList;
    }

    /**
     * Возвращает состояние процесса
     *
     * @return (true - завершин / false - не завершон)
     */
    public Boolean getFinished() {
        Boolean result = false;
        Collections.sort(this.tstepList);
        List<TStep> finishedTStep = this.tstepList.stream().filter(c -> c.getFinished() == true).collect(Collectors.toList());
        if (this.tstepList.size() == finishedTStep.size()) {
            result = true;
        }
        return result;
    }

    /**
     * Возвращает следующий по порядку не завершонный шаг прроцесса
     *
     * @return (TStep)
     */
    public TStep getNextTStep() {
        TStep chainStep = null;
        Collections.sort(this.tstepList);
        List<TStep> finishedList = this.tstepList.stream().filter(c -> c.getFinished() == false).collect(Collectors.toList());
        if (!finishedList.isEmpty()) {
            chainStep = finishedList.get(0);
        }
        return chainStep;
    }

    /**
     * Возвращает следующийе активности в процессе
     *
     * @return (List)
     */
    public List<TActivity> getNextTActivityList() {
        List<TActivity> result = new ArrayList<>();
        if (getNextTStep() != null) {
            result = getNextTStep().getNextTActivityList();
        }
        return result;
    }

   

}
