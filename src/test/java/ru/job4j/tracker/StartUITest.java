package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        MemTracker tracker = new MemTracker();
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(out));
        actions.add(new QuitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    public void whenReplaceItem() {
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(out));
        actions.add(new QuitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenDeleteItem() {
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(out));
        actions.add(new QuitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[]{"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(out));
        actions.add(new QuitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu: " + ln
                        + "0. Replace item" + ln
                        + "1. Quit" + ln
                        + "=== Edit item ===" + ln
                        + "Заявка успешно изменена" + ln
                        + "Menu: " + ln
                        + "0. Replace item" + ln
                        + "1. Quit" + ln
        );
    }

    @Test
    void whenFindAllItemsTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("Item to be found"));
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ShowAllAction(out));
        actions.add(new QuitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu: " + ln
                        + "0. Show all items" + ln
                        + "1. Quit" + ln
                        + "=== Show all items ===" + ln
                        + one + ln
                        + "Menu: " + ln
                        + "0. Show all items" + ln
                        + "1. Quit" + ln
        );
    }

    @Test
    public void whenFindByNameActionOutputTestIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("Find me if you can"));
        Input in = new StubInput(
                new String[]{"0", one.getName(), "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByNameAction(out));
        actions.add(new QuitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu: " + ln
                        + "0. Find by name" + ln
                        + "1. Quit" + ln
                        + "=== Find item by name ===" + ln
                        + one + ln
                        + "Menu: " + ln
                        + "0. Find by name" + ln
                        + "1. Quit" + ln
        );
    }

    @Test
    public void whenFindByIdActionOutputTestIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item one = tracker.add(new Item("Find my id if you can"));
        Input in = new StubInput(
                new String[]{"0", String.valueOf(one.getId()), "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByIdAction(out));
        actions.add(new QuitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu: " + ln
                        + "0. Find by id" + ln
                        + "1. Quit" + ln
                        + "=== Find by id ===" + ln
                        + one + ln
                        + "Menu: " + ln
                        + "0. Find by id" + ln
                        + "1. Quit" + ln
        );
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"7", "0"}
        );
        MemTracker tracker = new MemTracker();
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new QuitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu: " + ln
                        + "0. Quit" + ln
                        + "Wrong input, you can select: 0... 0" + ln
                        + "Menu: " + ln
                        + "0. Quit" + ln
        );
    }
}



