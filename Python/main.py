from Note import Note
from Notebook import Notebook
from View import View

def run():
    notebook = Notebook('notes.json')
    view = View()
    while True:
        view.show_menu()
        option = input("Choose an option: ")
        if option == '1':
            view.show_notes(notebook.get_notes())
        elif option == '2':
            title, body = view.get_note_data()
            notebook.add_note(title, body)
        elif option == '3':
            view.show_notes(notebook.get_notes())
            note_id = input("Enter the note ID to delete: ")
            if not note_id.isdigit() or int(note_id) < 1 or int(note_id) > len(notebook.get_notes()):
                print("Invalid note ID. Please enter a valid number.")
                continue
            notebook.delete_note(int(note_id) - 1) 
        elif option == '4':
            view.show_notes(notebook.get_notes())
            note_id = input("Enter the note ID to edit: ")
            if not note_id.isdigit() or int(note_id) < 1 or int(note_id) > len(notebook.get_notes()):
                print("Invalid note ID. Please enter a valid number.")
                continue
            title, body = view.get_note_data()
            notebook.edit_note(int(note_id) - 1, title, body)
        elif option == '5':
            break
        else:
            print("Unknown option")

if __name__ == "__main__":
    run()
