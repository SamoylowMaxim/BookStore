CREATE TABLE BOOKS (
  ID INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  NAME VARCHAR(255) NOT NULL,
  AUTHOR VARCHAR(255) NOT NULL,
  BOOK_LANGUAGE VARCHAR(255) NOT NULL,
  PUBLISH_YEAR INT NOT NULL,
  GENRE VARCHAR(255) NULL,
  ISBN VARCHAR(255) NOT NULL,
  PRICE DOUBLE NOT NULL,
  PAGES INT NOT NULL,
  ANNOTATION VARCHAR(100) NULL,
  RATING INT NULL,
  IS_NEW BOOLEAN NOT NULL,
  AMOUNT INT NOT NULL,
  COVER INT NULL
);

INSERT INTO BOOKS (NAME, AUTHOR, BOOK_LANGUAGE, PUBLISH_YEAR, GENRE, ISBN, PRICE, PAGES, ANNOTATION, RATING, IS_NEW, AMOUNT, COVER)
VALUES (
  'Война и мир',
  'Л.Н.Толстой',
  'Русский',
  2022,
  'Роман',
  '978-5-389-06256-6',
  500,
  1300,
  'Хорошо известный классический роман-эпопея Льва Толстого о эпохе завоевательных походов Наполеона.',
  9,
  true,
  3,
  1
);