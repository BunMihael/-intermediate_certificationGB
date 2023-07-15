import view
from notebook import Notebook

def run():
    notebook = Notebook("notes.json")
    while True:
        choice = view.show_menu()
        if choice == 1:
            view.show_notes(notebook.notes)
        elif choice == 2:
            title, body = view.create_note()
            notebook.create_note(title, body)
        elif choice == 3:
            note_id = view.select_note()
            notebook.delete_note(note_id)
        elif choice == 4:
            note_id, title, body = view.edit_note()
            notebook.edit_note(note_id, title, body)
        elif choice == 5:
            print("Exiting the application.")
            break
        else:
            print("Unknown choice. Please try again.")

if __name__ == '__main__':
    run()
