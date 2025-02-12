using System;
using System.Collections.Generic;

namespace LibraryManagementSystem
{
    class Book
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Author { get; set; }
        public int Year { get; set; }

        public override string ToString()
        {
            return $"ID: {Id}, Title: {Title}, Author: {Author}, Year: {Year}";
        }
    }

    class Library
    {
        private List<Book> books;
        private int nextId;

        public Library()
        {
            books = new List<Book>();
            nextId = 1;
        }

        public void AddBook(string title, string author, int year)
        {
            Book book = new Book
            {
                Id = nextId++,
                Title = title,
                Author = author,
                Year = year
            };
            books.Add(book);
            Console.WriteLine("Book added successfully!");
        }

        public void DisplayAllBooks()
        {
            if (books.Count == 0)
            {
                Console.WriteLine("No books available in the library.");
                return;
            }

            foreach (var book in books)
            {
                Console.WriteLine(book);
            }
        }

        public void SearchBook(string title)
        {
            var foundBooks = books.FindAll(b => b.Title.Equals(title, StringComparison.OrdinalIgnoreCase));
            if (foundBooks.Count == 0)
            {
                Console.WriteLine("No books found with the given title.");
            }
            else
            {
                foreach (var book in foundBooks)
                {
                    Console.WriteLine(book);
                }
            }
        }

        public void DeleteBook(int id)
        {
            var bookToRemove = books.Find(b => b.Id == id);
            if (bookToRemove != null)
            {
                books.Remove(bookToRemove);
                Console.WriteLine("Book deleted successfully!");
            }
            else
            {
                Console.WriteLine("Book not found with the given ID.");
            }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Library library = new Library();
            int choice;

            do
            {
                Console.WriteLine("\nLibrary Management System");
                Console.WriteLine("1. Add Book");
                Console.WriteLine("2. Display All Books");
                Console.WriteLine("3. Search Book");
                Console.WriteLine("4. Delete Book");
                Console.WriteLine("5. Exit");
                Console.Write("Enter your choice: ");
                choice = int.Parse(Console.ReadLine());

                switch (choice)
                {
                    case 1:
                        Console.Write("Enter Title: ");
                        string title = Console.ReadLine();
                        Console.Write("Enter Author: ");
                        string author = Console.ReadLine();
                        Console.Write("Enter Year: ");
                        int year = int.Parse(Console.ReadLine());
                        library.AddBook(title, author, year);
                        break;

                    case 2:
                        library.DisplayAllBooks();
                        break;

                    case 3:
                        Console.Write("Enter Title to Search: ");
                        string searchTitle = Console.ReadLine();
                        library.SearchBook(searchTitle);
                        break;

                    case 4:
                        Console.Write("Enter Book ID to Delete: ");
                        int id = int.Parse(Console.ReadLine());
                        library.DeleteBook(id);
                        break;

                    case 5:
                        Console.WriteLine("Exiting the system...");
                        break;

                    default:
                        Console.WriteLine("Invalid choice! Please try again.");
                        break;
                }
            } while (choice != 5);
        }
    }
}