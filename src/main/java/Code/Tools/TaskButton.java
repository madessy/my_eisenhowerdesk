package Code.Tools;

import Code.Model.Task;

public class TaskButton extends javafx.scene.control.Button
{
    Task t = null;

    /**
     *
     * @param t
     */
    public void setTask(Task t) {
        this.t = t;
    }

    /**
     *
     * @return
     */
    public Task getTask() {
        return this.t;
    }

}
