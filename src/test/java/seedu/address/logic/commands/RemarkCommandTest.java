package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

class RemarkCommandTest {

    private static final String REMARK_STUB = "Some remark";
    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRemark(REMARK_STUB).build();

        RemarkCommand remarkCommand = new RemarkCommand(INDEX_FIRST_PERSON, editedPerson.getRemark().value);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        CommandTestUtil.assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }
}