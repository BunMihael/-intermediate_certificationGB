class View:
    @staticmethod
    def show_menu():
        print("\n1. View notes")
        print("2. Add note")
        print("3. Delete note")
        print("4. Edit note")
        print("5. Exit")

    @staticmethod
    def show_notes(notes):
        if len(notes) == 0:
            print("No notes available. Please create a note first.")
        else:
            for i, note in enumerate(notes, start=1):  # start index from 1
                print(f"ID: {i}\nTitle: {note.title}\nBody: {note.body}\nTimestamp: {note.timestamp}\n")

    @staticmethod
    def get_note_data():
        title = input("Enter the note title: ")
        body = input("Enter the note body: ")
        return title, body
