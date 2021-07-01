import csv
import unittest
from dataclasses import dataclass
from typing import List


@dataclass
class PlainCsv:
    separator: str = ','
    quote: str = '"'

    def write(self, data: List[List[str]], path):
        with open(path, 'w', newline='') as file:
            csv_writer = csv.writer(file, delimiter=self.separator, quotechar=self.quote)
            csv_writer.writerows(data)

    def read(self, path):
        with open(path, 'r', newline='') as file:
            csv_reader = csv.reader(file, delimiter=self.separator, quotechar=self.quote)
            return [row for row in csv_reader]


class TestPLainCSV(unittest.TestCase):

    def test_equals(self):
        data = [['Number', 'Letter', 'Word', 'Sentence'],
                ['1', 'A', 'One', 'Line\nAnd another Line'],
                ['2', 'B', 'Two', "\"I'm being quoted\""]]

        path = '../resources/test_plaincsv_python.csv'

        plain_csv = PlainCsv()

        plain_csv.write(data, path)

        collected_data = plain_csv.read(path)

        self.assertListEqual(data, collected_data)
