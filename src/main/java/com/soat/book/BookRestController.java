package com.soat.book;

import com.soat.category.Category;
import com.soat.category.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

	public static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);

	@Autowired
	private BookServiceImpl bookService;

	@PostMapping("/addBook")

	public ResponseEntity<BookDTO> createNewBook(@RequestBody BookDTO bookDTORequest) {
		//, UriComponentsBuilder uriComponentBuilder
		Book existingBook = bookService.findBookByIsbn(bookDTORequest.getIsbn());
		if (existingBook != null) {
			return new ResponseEntity<BookDTO>(HttpStatus.CONFLICT);
		}
		Book bookRequest = mapBookDTOToBook(bookDTORequest);
		Book book = bookService.saveBook(bookRequest);
		if (book != null && book.getId() != null) {
			BookDTO bookDTO = mapBookToBookDTO(book);
			return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.CREATED);
		}
		return new ResponseEntity<BookDTO>(HttpStatus.NOT_MODIFIED);

	}

	@PutMapping("/updateBook")

	public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTORequest) {
		//, UriComponentsBuilder uriComponentBuilder
		if (!bookService.checkIfIdExists(bookDTORequest.getId())) {
			return new ResponseEntity<BookDTO>(HttpStatus.NOT_FOUND);
		}
		Book bookRequest = mapBookDTOToBook(bookDTORequest);
		Book book = bookService.updateBook(bookRequest);
		if (book != null) {
			BookDTO bookDTO = mapBookToBookDTO(book);
			return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
		}
		return new ResponseEntity<BookDTO>(HttpStatus.NOT_MODIFIED);
	}

	@DeleteMapping("/deleteBook/{bookId}")

	public ResponseEntity<String> deleteBook(@PathVariable Integer bookId) {
		bookService.deleteBook(bookId);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/searchByTitle")

	public ResponseEntity<List<BookDTO>> searchBookByTitle(@RequestParam("title") String title,
														   UriComponentsBuilder uriComponentBuilder) {
		List<Book> books = bookService.findBooksByTitleOrPartTitle(title);
		if (!CollectionUtils.isEmpty(books)) {
			// on retire tous les élts null que peut contenir cette liste => pour éviter les
			// NPE par la suite
			books.removeAll(Collections.singleton(null));
			List<BookDTO> bookDTOs = books.stream().map(book -> {
				return mapBookToBookDTO(book);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<BookDTO>>(bookDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<BookDTO>>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/searchByIsbn")

	public ResponseEntity<BookDTO> searchBookByIsbn(@RequestParam("isbn") String isbn,
													UriComponentsBuilder uriComponentBuilder) {
		Book book = bookService.findBookByIsbn(isbn);
		if (book != null) {
			BookDTO bookDTO = mapBookToBookDTO(book);
			return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
		}
		return new ResponseEntity<BookDTO>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Transforme un Book en BookDTO
	 *
	 * @param book
	 * @return
	 */
	private BookDTO mapBookToBookDTO(Book book) {
		ModelMapper mapper = new ModelMapper();
		BookDTO bookDTO = mapper.map(book, BookDTO.class);
		if (book.getCategory() != null) {
			bookDTO.setCategory(new CategoryDTO(book.getCategory().getCode(), book.getCategory().getLabel()));
		}
		return bookDTO;
	}

	/**
	 * Transforme un BookDTO en Book
	 *
	 * @param bookDTO
	 * @return
	 */
	private Book mapBookDTOToBook(BookDTO bookDTO) {
		ModelMapper mapper = new ModelMapper();
		Book book = mapper.map(bookDTO, Book.class);
		book.setCategory(new Category(bookDTO.getCategory().getCode(), ""));
		book.setRegisterDate(LocalDate.now());
		return book;
	}

}
