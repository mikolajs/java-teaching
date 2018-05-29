#include "zmienne.h"
#if MACHINETYPE == 8300
	#include "..\\include\\8300lib.h"
#elif MACHINETYPE == 8200
	#include "..\\include\\8200lib.h"
#elif MACHINETYPE == 8000
	#include "..\\include\\8000lib.h"
#elif MACHINETYPE == 8400
	#include "..\\include\\8400lib.h"
#elif MACHINETYPE == 8600
	#include "..\\include\\8600lib.h"
#endif

#if MACHINETYPE == 8600
	#include <type.h>
#endif
#include <ucos.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>
#include "main.h"
#include "transmisja.h"
#include "ekran.h"
#include "komunikat.h"

const KONFIGURACJA_UST KonfiguracjaUst[] = {
//PROGRAM
	{"SKRACAJ_KODY_AUTOMATYCZNIE", "PytanieSkrocicKod.", 0, 1, 0, 0, 0, ""},
	{"SUMOWANIE_ILOŒCI", "IloscSumowanie.", 0, 1, 0, 0, 0, ""},
	{"ILOŒÆ_DOMYŒLNA", "IloscDomyslna.", 0, 0, 0, 254, 0, ""},
	{"ILOŒÆ_AUTOMATYCZNIE", "IloscAutomatycznie.", 0, 1, 0, 0, 0, ""},
	{"NOWE_TOWARY", "TowarNieznany.", 0, 1, 0, 0, 0, ""},
	{"NOWE_TOWARY_DO_BAZY", "TowarDopisacDoBazy.", 0, 1, 0, 0, 0, ""},
	//{"OPIS_DO_DOKUMENTU", "PytanieOpisDoDokumentu.", 0, 1, 0, 1, 0, ""},
	//{"OPIS_DO_POZYCJI", "PytanieOpisDoPozycji.", 0, 1, 0, 1, 0, ""},
	{"PYTAJ_O_DATÊ", "EdycjaData.", 0, 1, 0, 1, 0, ""},
	{"KASUJ_PLIK_PO_WYS£ANIU", "PytanieKasowaniePoTransmisji.", 0, 1, 0, 0, 0, ""},
	{"ZEGAR_W_MENU_G£ÓWNYM", "WyswietlData.", 0, 1, 0, 0, 0, ""},
	{"NOWI_KONTRAHENCI", "DokumentKontrahentPoza.", 0, 1, 0, 0, 0, ""},
	{"NOWE_RODZAJE_DOKUMENTU", "DokumentRodzajDokumentuPoza.", 0, 1, 0, 0, 0, ""},
	{"KOMPLETACJA_CENA_NETTO", "KompletacjaCenaNetto.", 0, 1, 0, 0, 0, ""},

//TRANSMISJA
	{"TRANSMISJA_PROTOKÓ£", "TransmisjaProtokol.", 0, 4, 1, 2, 0, ""},
	{"TRANSMISJA_INTERJFEJS", "TransmisjaPort.", 0, 5, 1, 3, 0, ""},
	{"TRANSMISJA_SZYBKOŒÆ", "TransmisjaPredkosc.", 0, 6, 1, 5, 0, ""},

//D£UGOŒCI BAZA TOWAROWA
	{"D£UGOŒÆ_KOD_KRESKOWY", "BazaTowarKod.", 0, 0, 0, 30, 0, ""},
	{"D£UGOŒÆ_NAZWA_TOWARU", "BazaTowarNazwa.", 0, 0, 0, 50, 0, ""},
	{"D£UGOŒÆ_OPIS_TOWARU", "BazaTowarOpis.", 0, 0, 0, 50, 0, ""},
	{"D£UGOŒÆ_STAN", "BazaTowarStan.", 0, 0, 0, 10, 0, ""},
	{"D£UGOŒÆ_CENA", "BazaTowarCena.", 0, 0, 0, 10, 0, ""},
	{"D£UGOŒÆ_JM", "BazaTowarJm.", 0, 0, 0, 6, 0, ""},
	{"CENA_W_GR","BazaTowarCenaGrosze.", 0, 1, 0, 0, 0, ""},

//D£UGOŒCI BAZA KONTRAHENTÓW
	{"D£UGOŒÆ_ID_KONTRAHENTA", "BazaKontrahentId.", 0, 0, 0, 50, 0, ""},
	{"D£UGOŒÆ_OPIS_KONTRAHENTA", "BazaKontrahentOpis.", 0, 0, 0, 50, 0, ""},

//D£UGOŒCI BAZA RODZAJÓW DOKUMENTÓW
	{"D£UGOŒÆ_ID_RODZAJ_DOKUMENTU", "BazaRodzajDokumentuId.", 0, 0, 0, 30, 0, ""},
	{"D£UGOŒÆ_OPIS_RODZAJ_DOKUMENTU", "BazaRodzajDokumentuOpis.", 0, 0, 0, 30, 0, ""},

//D£UGOŒCI DOKUMENTY
	{"D£UGOŒÆ_ID_DOKUMENTU", "BazaDokumentId.", 0, 0, 10, 30, 0, ""},
	{"D£UGOŒÆ_ILOŒÆ", "BazaDokumentIlosc.", 0, 0, 0, 10, 0, ""},
	{"D£UGOŒÆ_OPIS_DOK", "BazaDokumentOpisDok.", 0, 0, 0, 30, 0, ""},
	{"D£UGOŒÆ_OPIS_POZ", "BazaDokumentOpisPoz.", 0, 0, 0, 30, 0, ""},
	{"D£UGOŒÆ_DATA_DOK", "BazaDokumentData.", 0, 0, 8, 14, 0, ""},

//TOWARY
	{"TOWAR_KOD_KRESKOWY", "DokumentWeTowarKod.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"TOWAR_NAZWA", "DokumentWeTowarNazwa.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"TOWAR_OPIS", "DokumentWeTowarOpis.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"TOWAR_STAN", "DokumentWeTowarStan.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"TOWAR_JM", "DokumentWeTowarJm.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"TOWAR_CENA", "DokumentWeTowarCena.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"TOWAR_SEPARATOR", "DokumentWeTowarSep.", 0, 0, 0, 127, 0, ""},

//KONTRAHENCI
	{"KONTRAHENT_ID", "DokumentWeKontrahentId.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"KONTRAHENT_OPIS", "DokumentWeKontrahentOpis.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"KONTRAHENT_SEPARATOR", "DokumentWeKontrahentSep.", 0, 0, 0, 127, 0, ""},

//RODZAJE DOKUMENTÓW
	{"RODZAJ_DOKUMENTU_ID", "DokumentWeRodzajDokumentuId.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"RODZAJ_DOKUMENTU_OPIS", "DokumentWeRodzajDokumentuOpis.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"RODZAJ_DOKUMENTU_SEPARATOR", "DokumentWeRodzajDokumentuSep.", 0, 0, 0, 127, 0, ""},

//DOKUMENTY
	{"DOK_WE_ID_DOKUMENTU", "DokumentWeDokumentId.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WE_ID_RODZAJ_DOKUMENTU", "DokumentWeDokumentIdRodzaj.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WE_ID_KONTRAHENTA", "DokumentWeDokumentIdKontr.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WE_KOD_KRESKOWY", "DokumentWeDokumentKod.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WE_ILOŒÆ", "DokumentWeDokumentIlosc.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WE_OPIS_DOK", "DokumentWeDokumentOpisDok.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WE_OPIS_POZ", "DokumentWeDokumentOpisPoz.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WE_DATA", "DokumentWeDokumentData.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WE_SEPARATOR", "DokumentWeDokumentSep.", 0, 0, 0, 127, 0, ""},

//DOKUMENTY WYJŒCIOWE
	{"DOK_WY_ID_DOKUMENTU", "DokumentWyDokumentId.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_ID_RODZAJ_DOKUMENTU", "DokumentWyDokumentIdRodzaj.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_ID_KONTRAHENTA", "DokumentWyDokumentIdKontr.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_KOD_KRESKOWY", "DokumentWyDokumentKod.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_ILOŒÆ", "DokumentWyDokumentIlosc.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_OPIS_DOK", "DokumentWyDokumentOpisDok.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_OPIS_POZ", "DokumentWyDokumentOpisPoz.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_DATA", "DokumentWyDokumentData.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_NAZWA_TOWARU", "DokumentWyTowarNazwa.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_CENA", "DokumentWyTowarCena.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_STAN", "DokumentWyTowarStan.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_JM", "DokumentWyTowarJm.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_OPIS_TOWARU", "DokumentWyTowarOpis.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_OPIS_RODZAJ_DOKUMENTU", "DokumentWyRodzajDokOpis.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_OPIS_KONTRAHENTA", "DokumentWyKontrahentOpis.", 0, 0, 0, MAX_ROZMIAR_REKORDU, 0, ""},
	{"DOK_WY_SEPARATOR", "DokumentWySeparator.", 0, 0, 0, 127, 0, ""},

//CZYTNIK
	{"CZYTNIK_TRYB_PRACY", "", 1, 2, 0, 0, 20, "7654"},
	{"CZYTNIK_KODY_WAGOWE", "KodWagowy.", 0, 8, 0, 0, 0, ""},
	{"CZYTNIK_KOD_WAGOWY_I_PREFIKS", "KodWagowyDopiszPrefiks.", 0, 1, 0, 0, 0, ""},
	{"CZYTNIK_KOD_WAGOWY_UZUPE£NIENIE", "KodWagowyUzupelnienie.", 0, 1, 0, 0, 0, ""},
	{"CZYTNIK_WERYFIKACJA", "", 1, 7, 0, 0, 11, "32"},
	{"CZYTNIK_KODY_W_NEGATYWIE", "", 1, 1, 0, 0, 11, "4"},

//KODY KRESKOWE - ODCZYT
	{"KODY_UPC-E", "", 1, 1, 0, 0, 1, "6"},
	{"KODY_UPC-A", "", 1, 1, 0, 0, 1, "0"},
	{"KODY_EAN-8", "", 1, 1, 0, 0, 1, "3"},
	{"KODY_EAN-13", "", 1, 1, 0, 0, 1, "0"},
	{"KODY_DATABAR", "", 1, 1, 0, 0, 3, "6"},
	{"KODY_DATABAR_LIMITED", "", 1, 1, 0, 0, 2, "0"},
	{"KODY_CODE/EAN_128", "", 1, 1, 0, 0, 1, "7"},
	{"KODY_CODE_39", "", 1, 1, 0, 0, 0, "7"},
	{"KODY_INTERLEAVED_2/5", "", 1, 1, 0, 0, 0, "3"},
	{"KODY_INDUSTRIAL_2/5", "", 1, 1, 0, 0, 0, "4"},
	{"KODY_MATRIX_2/5", "", 1, 1, 0, 0, 0, "2"},
	{"KODY_FRENCH_PHARMA", "", 1, 1, 0, 0, 0, "5"},
	{"KODY_ITALIAN_PHARMA", "", 1, 1, 0, 0, 0, "6"},
	{"KODY_CODE_93", "", 1, 1, 0, 0, 0, "0"},
	{"KODY_CODABAR", "", 1, 1, 0, 0, 0, "1"},
	{"KODY_MSI", "", 1, 1, 0, 0, 2, "5"},
	{"KODY_PLESSEY", "", 1, 1, 0, 0, 2, "4"},
	{"KODY_TELEPEN", "", 1, 1, 0, 0, 2, "2"},

//KODY KRESKOWE - USTAWIENIA
	//UPC-E
		{"KODY_UPC-E_KONW_DO_UPC-A", "", 1, 1, 0, 0, 9, "1"},
		{"KODY_UPC-E_WYSYLAJ_SYSNUM", "", 1, 1, 0, 0, 10, "1"},
		{"KODY_UPC-E_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 10, "5"},
		{"KODY_UPC-E_DODATKI_2_ZN", "", 1, 1, 0, 0, 1, "5"},
		{"KODY_UPC-E_DODATKI_5_ZN", "", 1, 1, 0, 0, 1, "4"},
		{"KODY_UPC-E_OBSLUGA_UPC-E1", "", 1, 1, 0, 0, 4, "6"},
		{"KODY_UPC-E_UPC-E1_SPRAWDZ_3x", "", 1, 1, 0, 0, 11, "1"},

	//UPC-A
		{"KODY_UPC-A_KONW_DO_EAN13", "", 1, 1, 0, 0, 9, "0"},
		{"KODY_UPC-A_WYSY£AJ_SYSNUM", "", 1, 1, 0, 0, 10, "0"},
		{"KODY_UPC-A_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 10, "4"},
		{"KODY_UPC-A_DODATKI_2_ZN", "", 1, 1, 0, 0, 2, "7"},
		{"KODY_UPC-A_DODATKI_5_ZN", "", 1, 1, 0, 0, 2, "6"},

	//EAN-8
		{"KODY_EAN-8_KONW_DO_EAN13", "", 1, 1, 0, 0, 11, "7"},
		{"KODY_EAN-8_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 10, "3"},
		{"KODY_EAN-8_DODATKI_2_ZN", "", 1, 1, 0, 0, 1, "2"},
		{"KODY_EAN-8_DODATKI_5_ZN", "", 1, 1, 0, 0, 1, "1"},

	//EAN-13
		{"KODY_EAN-13_KONW_ISBN", "", 1, 1, 0, 0, 10, "0"},
		{"KODY_EAN-13_KONW_ISSN", "", 1, 1, 0, 0, 10, "6"},
		{"KODY_EAN-13_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 10, "2"},
		{"KODY_EAN-13_DODATKI_2_ZN", "", 1, 1, 0, 0, 2, "7"},
		{"KODY_EAN-13_DODATKI_5_ZN", "", 1, 1, 0, 0, 2, "6"},
		{"KODY_EAN-13_KONW_GTIN", "", 1, 1, 0, 0, 11, "5"},

	//Databar (Expanded)
		{"KODY_DATABAR_WYSY£AJ_CODEID", "", 1, 1, 0, 0, 3, "5"},
		{"KODY_DATABAR_WYSY£AJ_ECODEID", "", 1, 1, 0, 0, 4, "7"},
		{"KODY_DATABAR_WYSY£AJ_APPID", "", 1, 1, 0, 0, 3, "4"},
		{"KODY_DATABAR_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 3, "3"},

	//Databar Limited
		{"KODY_DATABAR-L_WYSY£AJ_CODEID", "", 1, 1, 0, 0, 3, "2"},
		{"KODY_DATABAR-L_WYSY£AJ_APPID", "", 1, 1, 0, 0, 3, "1"},
		{"KODY_DATABAR-L_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 3, "0"},

	//EAN-128
		{"KODY_CODE/EAN128_WYSY£AJ_FNC1", "", 0, 1, 0, 0, 62, ""},

	//Code 39
		{"KODY_CODE39_ZNAKI_ASCII", "", 1, 1, 0, 0, 5, "4"},
		{"KODY_CODE39_WYSY£AJ_START/STOP", "", 1, 1, 0, 0, 5, "7"},
		{"KODY_CODE39_WERYFIKUJ_CHECKDIG", "", 1, 1, 0, 0, 5, "6"},
		{"KODY_CODE39_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 5, "5"},

	//Interleav. 2/5
		{"KODY_INTERL2/5_WERYFIKUJ_CHECKDIG", "", 1, 1, 0, 0, 5, "1"},
		{"KODY_INTERL2/5_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 5, "0"},

	//Industrial 2/5
		{"KODY_INDUST2/5_WERYFIKUJ_CHECKDIG", "", 1, 1, 0, 0, 6, "7"},
		{"KODY_INDUST2/5_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 6, "6"},

	//Matrix 25
		{"KODY_MATRIX2/5_WERYFIKUJ_CHECKDIG", "", 1, 1, 0, 0, 6, "5"},
		{"KODY_MATRIX2/5_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 6, "4"},

	//CIP = French Pharamacode
		{"KODY_FRENCH-PHARMA_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 5, "2"},

	//Italia Pharamacode
		{"KODY_ITALIAN-PHARMA_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 5, "3"},

	//Codebar (NW7)
		{"KODY_CODABAR_START/STOP", "", 1, 3, 0, 0, 7, "54"},
		{"KODY_CODABAR_WYSY£AJ_START/STOP", "", 1, 1, 0, 0, 7, "3"},

	//Plessey
		{"KODY_PLESSEY_WYSY£AJ_CHECKDIG", "", 1, 1, 0, 0, 9, "3"},
		{"KODY_PLESSEY_KONW_DO_UK_PLESSEY", "", 1, 1, 0, 0, 9, "2"},

	//Telepen
		{"KODY_TELEPEN_NUMERYCZNY", "", 1, 1, 0, 0, 2, "1"}
};

const KONFIGURACJA_WAR KonfiguracjaWar[] = {
	{1, "Tak", "1"},
	{1, "Nie", "0"},
	{1, "TAK", "1"},
	{1, "NIE", "0"},
	{1, "tak", "1"},
	{1, "nie", "0"},
	{2, "Automatyczny", "0000"},
	{2, "automatyczny", "0000"},
	{2, "AUTOMATYCZNY", "0000"},
	{2, "Ci¹g³y", "0001"},
	{2, "ci¹g³y", "0001"},
	{2, "CI¥G£Y", "0001"},
	{2, "Ponawialny", "0010"},
	{2, "ponawialny", "0010"},
	{2, "PONAWIALNY", "0010"},
	{2, "W³¹cz/Wy³¹cz", "0011"},
	{2, "w³¹cz/wy³¹cz", "0011"},
	{2, "W£¥CZ/WY£¥CZ", "0011"},
	{2, "Chwilowy", "0100"},
	{2, "chwilowy", "0100"},
	{2, "CHWILOWY", "0100"},
	{2, "Powtarzalny", "0101"},
	{2, "powtarzalny", "0101"},
	{2, "POWTARZALNY", "0101"},
	{2, "Laser", "0110"},
	{2, "laser", "0110"},
	{2, "LASER", "0110"},
	{2, "Testowy", "0111"},
	{2, "testowy", "0111"},
	{2, "TESTOWY", "0111"},
	{2, "Celowanie", "1000"},
	{2, "celowanie", "1000"},
	{2, "CELOWANIE", "1000"},
	{3, "abcd/abcd", "00"},
	{3, "abcd/tn*e", "01"},
	{3, "ABCD/ABCD", "10"},
	{3, "ABCD/TN*E", "11"},
	{4, "Novitus", "1"},
	{4, "novitus", "1"},
	{4, "NOVITUS", "1"},
	{4, "Cipherlab", "2"},
	{4, "CipherLab", "2"},
	{4, "cipherlab", "2"},
	{4, "CIPHERLAB", "2"},
	{5, "Kabel", "0"},
	{5, "KABEL", "0"},
	{5, "kabel", "0"},
	{5, "IR", "2"},
	{5, "ir", "2"},
	{5, "IrDA", "3"},
	{5, "IRDA", "3"},
	{5, "irda", "3"},
	{5, "Dok+USB_HID", "7"},
	{5, "DOK+USB_HID", "7"},
	{5, "dok+usb_hid", "7"},
	{5, "Dok+USB", "8"},
	{5, "DOK+USB", "8"},
	{5, "dok+usb", "8"},
	{5, "Dok+USB_CDC", "10"},
	{5, "DOK+USB_CDC", "10"},
	{5, "dok+usb_cdc", "10"},
	{6, "115200", "1"},
	{6, "57600", "2"},
	{6, "38400", "3"},
	{6, "19200", "4"},
	{6, "9600", "5"},
	{7, "0", "00"},
	{7, "1", "01"},
	{7, "2", "10"},
	{7, "3", "11"},
	{8, "Wy³¹czone","0"},
	{8, "wy³¹czone","0"},
	{8, "WY£¥CZONE","0"},
	{8, "2XCCCCXWWWWWX","1"},
	{8, "2xccccxwwwwwx","1"},
	{8, "2XCCCCSWWWWWX","2"},
	{8, "2xccccswwwwwx","2"},
	{8, "2XCCCCCWWWWWX","3"},
	{8, "2xcccccwwwwwx","3"},
	{8, "2XXCCCCWWWWWX","4"},
	{8, "2xxccccwwwwwx","4"},
	{8, "2XXCCCXWWWWWX","5"},
	{8, "2xxcccxwwwwwx","5"},
	{8, "2XCCCCWWWWWWX","6"},
	{8, "2xccccwwwwwwx","6"},
	{8, "2XCCCXWWWWWWX","7"},
	{8, "2xcccxwwwwwwx","7"},
	{8, "2XXCCCWWWWWWX","8"},
	{8, "2xxcccwwwwwwx","8"}
};

#define MEM_TMP 2048 //=2kB ile wolnego RAM-u musi zawsze pozostac = wolny RAM - (baza+dokument)
#define MEM_DOC_TMP	4096 //=4kB ile wolnego RAM-u na dokument po za³adowaniu bazy

#define WRITE_MAX_RETRIES_COUNT 150       //maksymalna liczba prob wysylania

#define ETX 0x03
#define EOT 0x04
#define ENQ 0x05
#define ACK2 0x06
#define CUT 0x07
#define TLF 0x08
#define ABT 0x09
#define RST 0x0A		//rozpocznij transmisjê od nowa (bo np. zbyt du¿o pakietów o z³ych numerach)
#define BAD 0x0B		//z³y format pliku (np. z³a d³ugoœæ)
#define IRD 0x0C		//transmsisja przez IrDê
#define NAK2 0x15
#define ANY 0x5A

static char TempBuff[3], Ack = ACK2, Nak = NAK2, Tlf = TLF, Abt = ABT, Bad = BAD, Enq = ENQ, Eot = EOT,/* Dc1 = DC1,*/ Cut = CUT, Any = ANY, Rst = RST, Ird = IRD;
static char EndOfTx[3] = {0x00, 0xFF, 0};

static unsigned char ProtPktNo = 0;
static int ProtComId=0;
static int TF_PortType=0;
static int odbieramy_DB_IDX=0;
static long NoBytes=0, Progress=0, recLen=0, file_size=0;
static long _msec = 0;
static int wait=0;

//==================================================================================
void TF_EndOfTx(void)
{
	_WriteCom(EndOfTx, 3);
}
void TF_CutTrans(void)
{
	_WriteCom(&Cut, 1);
}
void TF_EoTrans(void)
{
	nwrite_com(ProtComId, &Abt, 1);	//by transmisji IrDA nie trzeba by³o przerywaæ 2xESC
}
void TF_LargeFile(void)
{
	_WriteCom(&Tlf, 1);
}
void TF_ANY(void)
{
	_WriteCom(&Any, 1);
}
void TF_IrDA(void)
{
	_WriteCom(&Ird, 1);
}
//==================================================================================
void TF_PrintAt(int x, int y, char* Msg)
{
	gotoxy(x,y+PoczatekMenu_Y); printfN((char*)Msg, 1);
}
void TF_ShowSandGlass(void)
{
	#if (MACHINETYPE == 8000)
		WaitHourglass((100-24)/2, 40, HOURGLASS_24x23);
	#elif (MACHINETYPE == 8300)
		WaitHourglass((128-24)/2, 40, HOURGLASS_24x23);
	#elif (MACHINETYPE == 8200 || MACHINETYPE == 8400)
		WaitHourglass((160-24)/2, (160-23)/2, HOURGLASS_24x23);
	#elif (MACHINETYPE == 8600)
		WaitHourglass((239-24)/2, (320-23)/2, HOURGLASS_24x23);
	#endif
}
void DispProgress(int waiting)
{
	char szB[128];

	static unsigned long czestotl_odsw=0;

	if (waiting==1 && wait==1)
	{
		return;	
	}

	if (czestotl_odsw+100 > sys_msec)
		return;

	czestotl_odsw = sys_msec;
	wait = waiting;

	sprintf(szB,"Wykonano: %ld%% ", 100*Progress/NoBytes);

	#if MACHINETYPE == 8000
		if (waiting)
		{
			clr_rect(0, 8, 96, 56);
			TF_PrintAt(0, 2, (char *)"Oczekiwanie na");
			TF_PrintAt(0, 3, (char *)"po³¹czenie...");
		}
		else if (Progress > 0)
		{
			TF_PrintAt(0, 2, (char *)"Trwa transmisja ");
		}
	#elif MACHINETYPE == 8600
		if (waiting)
		{
			//gotoxy(0,3); clr_eol();
			//gotoxy(0,4); clr_eol();
			//gotoxy(0,5); clr_eol();
			//gotoxy(0,6); clr_eol();
			//gotoxy(0,7); clr_eol();
			//gotoxy(0,8); clr_eol();
			//gotoxy(0,9); clr_eol();
			//gotoxy(0,10); clr_eol();
			//gotoxy(0,11); clr_eol();

			TF_PrintAt(0, 2, (char *)"Oczekiwanie na      ");
			TF_PrintAt(0, 3, (char *)"po³¹czenie...");
		}
		else if (Progress > 0)
		{
			TF_PrintAt(0, 2, (char *)"Trwa transmisja...  ");
		}
	#else	  
		if (waiting)
		{
			clr_rect(0, 8, 120, 56);
			TF_PrintAt(0, 2, (char *)"Oczekiwanie na      ");
			TF_PrintAt(0, 3, (char *)"po³¹czenie...");
		}
		else if (Progress > 0)
		{
			TF_PrintAt(0, 2, (char *)"Trwa transmisja...  ");
		}
	#endif

	if (!waiting)
	{
		if (NoBytes > 0 && Progress > 0)
		{
			TF_PrintAt(0, 3, (char *)szB);
		}
	}
}
void TF_ClosePort(int PortId)
{
	close_com(PortId);
}
int TF_OpenPort(void)
{
	int PortTypeId=0;
	int PortSettings=0;

	switch (Konfiguracja.TransmisjaPredkosc)
	{
		case 1:  PortSettings = 0x08;  break;               // 115200,N,8,1
		case 2:  PortSettings = 0x0a;  break;               // 57600
		case 3:  PortSettings = 0x0b;  break;               // 38400
		case 4:  PortSettings = 0x0c;  break;               // 19200
		case 5:  PortSettings = 0x0d;  break;               // 9600
	}

	switch(Konfiguracja.TransmisjaPort)
	{
		case COMM_DIRECT:
			SetCommType(1, 0);
			PortTypeId = 1;
			break;

		#if MACHINETYPE != 8600
			case COMM_DOCKING:
				SetCommType(1, 1);
				PortTypeId = 1;
				break;

			case COMM_IR:
				#if (MACHINETYPE == 8000 || MACHINETYPE == 8300)
					SetCommType(1, 2);
					PortTypeId = 1;
				#else
					SetCommType(2, 2);
					PortTypeId = 2;
				#endif
				break;

			#if (MACHINETYPE == 8000 || MACHINETYPE == 8300)
				case COMM_IrDA:
					SetCommType(1, 3);
					PortTypeId = 1;
					break;
			#endif
		#endif

		case 7:				//COMM_USBHID:
		case 8:				//COMM_USBVCOM:
		case 9:				//COMM_USBDISK:
		case 10:			//COMM_USBVCOM_CDC:
			SetCommType(5, Konfiguracja.TransmisjaPort);
			PortTypeId = 5;
			break;

		default:
			return -1;
	}

	if(open_com(PortTypeId, PortSettings))
	{
		TF_PortType = PortTypeId;
		return PortTypeId;
	}
	else
	{
		TF_PortType = -1;
		return -1;
	}
}
void TF_InitProt(int ComId)
{
	ProtComId = ComId;
	ProtPktNo = 0;
}
void DiagnosticMsg(int ErrCtrl, int CommId)
{
	Czysc();

	if(ErrCtrl == -1) // "Naciœniêto ESC! Transmisja      przerwana!      Naciœnij klaw."
	{
		TF_EoTrans();
		Komunikat(11,0);
	}

	else if(ErrCtrl == -2) //"Przekroczony czas oczekiwania"
	{
		TF_CutTrans();
		Komunikat(12,0);
	}

	else if(ErrCtrl == -10) //"B³¹d! Za du¿y plik!"
	{
		TF_LargeFile();	//znak do programu na PC o za duzym pliku
		Komunikat(13,0);
	}

	else if(ErrCtrl == -11) //"B³¹d! Brak miejsca"
	{
		Komunikat(14,0);
	}

	else if(ErrCtrl == -15)	//brak b³êdu ;)
	{
		//TF_OkChoice(1,0);
	}
	else
	{
		Komunikat(37,ErrCtrl); //jakieœ inne b³êdy???
	}
	TF_ClosePort(CommId);
}
int _ReadCom(char *Buff, int Len, long TimeOut)
{
	int i = 0;
	long msec=0, msec2=0;
	char c=0, key=0;

	msec = sys_msec + TimeOut;
	msec2= sys_msec + 100;
	for(i = 0; i < Len;)
	{
		if( read_com(ProtComId, &c) > 0 )
			Buff[i++] = c;

		if (sys_msec>msec2)
		{
			DispProgress(1);	//potrzebne dla transmisji przez IrDÊ
		}

		if(sys_msec > msec)		//jeœli timeout
		{
			return -2;
		}

		if (Klawisz(getchar(), (char*)"ESC"))		//przerwanie transmisji (przez ESC) dla pozosta³ych interfejsów
			return -1;
	}

	return i;
}
int _WriteCom(const char *Buff, int Len)
{
	long msec = 0, msec2 = 0;

	msec = sys_msec + 10000;	//timeout
	msec2 = sys_msec + 100;	//czas po którym wyœwietliæklepsydrê
	nwrite_com(ProtComId, (char*)Buff, Len);

	while(!com_eot(ProtComId))	//czekaj a¿ dane zostan¹ wys³ane //rafal
	{
		if (Klawisz(getchar(), (char*)"ESC"))		//przerwanie transmisji (przez ESC) dla pozosta³ych interfejsów
		{
			return -1;
		}

		if (sys_msec > msec2)
		{
			DispProgress(1);	//potrzebne dla transmisji przez IrDÊ
		}

		if (sys_msec > msec)	//potrzebne dla transmisji przez IrDÊ
		{
			return -2;
		}
	}

	return Len;
}
int CreateFile(const char *file_name)
{
	if (access(file_name)) remove(file_name);
	return open(file_name);
}
void RemoveFile(int file_handle, const char *file_name)
{
	close(file_handle);
	remove((char *)file_name);
}
//==================================================================================
int TF_ReadPkt(char *Buff)
{
	char Crc = 0;
	char ForeHead[3];
	int ans, i;
	unsigned char Len;
	long czas_startu;

	czas_startu=sys_msec;
	ProtPktNo++;

	while(sys_msec<czas_startu+10000)
	{
		if((ans=_ReadCom(ForeHead, 3, 50)) > 0)		//250ms na odebranie nag³ówka pakietu
		{
			Len = (unsigned char)ForeHead[0];

			if(Len == 0 && ForeHead[1] == -1)
			{
				return 0;
			}

			if((ans=_ReadCom(Buff, (int)Len, 80)) > 0)		//400ms na odebranie danych pakietu
			{
				for(i = 0, Crc = (unsigned char)0xFF; i < Len; i++)	//wylicz sumê kontroln¹ odebranego pakietu
				{
					Crc ^= Buff[i];
				}
				if(Crc == ForeHead[1])
				{
					if((unsigned char)ForeHead[2] == ProtPktNo)
					{
						if (_WriteCom(&Ack, 1)==-1)
						{
							return -1;
						}
						return (int)Len;
					}
					else if((unsigned char)ForeHead[2] == ProtPktNo - 1)
					{
						if (_WriteCom(&Ack, 1)==-1) return -1;
					}                    
					else	//odebrano dobry pakiet, ale o z³ym numerze
					{
						while (_ReadCom (ForeHead, 1, 10) == 1);		//poczekaj a¿ "komputer" przestanie nadawaæ (50ms)!!!
						if (_WriteCom(&Nak, 1)==-1) return -1;
					}
				}
				else		//CRC siê nie zgadza (mo¿e zaczêto odbieraæ w trakcie nadawania pakietu?)
				{
					while (_ReadCom (ForeHead, 1, 10) == 1);		//poczekaj a¿ "komputer" przestanie nadawaæ (50ms)!!!
				}
			}
			else	//nie odebrano ca³ego pakietu
			{
				if (ans==-1)	//naciœniêto ESC (tylko dla IrDA)
				{
					return -1;
				}
			}		
		}
		else if (ans==-1)	//naciœniêto ESC (tylko dla IrDA)
		{
			return -1;
		}
		else //nie odebrano nawet nag³ówka pakietu
		{
			DispProgress(1);
		}
	}

	return -2;	//timeout
}
int TF_WritePkt_Small(const char *Buff, unsigned char Len)
{
	int i, Retries = 0, status_pol;
	char Crc;
	char key;

	DispProgress(0);	// 209

	for(i = 0, Crc = 0xFF; i < Len; i++)
	{
		Crc^= Buff[i];
	}

	ProtPktNo++;

	while(TRUE)
	{
		if (Klawisz(getchar(), (char*)"ESC"))		//przerwanie transmisji (przez ESC) dla pozosta³ych interfejsów
		{
			return -1;
		}

		clear_com(ProtComId);

		//It stops at the next function call while COMM_IrDA is sellected.
		//********************************
		status_pol = _WriteCom((char *)&Len, 1);         //wyslanie dlugosci
		if(status_pol < 0) return status_pol;

		status_pol = _WriteCom(&Crc, 1);                 //sumy kontrolnej
		if(status_pol < 0) return status_pol;

		status_pol = _WriteCom((char *)&ProtPktNo, 1);   //wyslanie numeru pakietu
		if(status_pol < 0) return status_pol;

		if(Len == 0)
		{
			return 0;
		}

		status_pol = _WriteCom(Buff, Len);   //wysylanie danych do komputera

		if( status_pol < 1 )
		{
			return status_pol;
		}

		status_pol = _ReadCom(TempBuff, 1, 200);			//Timeout 1000 ms, zczytanie potwierdzenia
		if((status_pol != -1) && (status_pol != -2))
		{
			Retries = 0;
			if(TempBuff[0] == ACK2)
			{
				return(Len);
			}
		}
		else
		{
			Retries++;
			if(status_pol == -1)
			{
				return status_pol;
			}
		}

		if( Retries > WRITE_MAX_RETRIES_COUNT )
		{
			return status_pol;
		}
	}//enf of while(Retries < WRITE_MAX_RETRIES_COUNT)
}
int TF_WritePkt(const char *Buff, int Len)
{
	int LenMax = 254;
	int wynik = 0;

	if(Len>LenMax)
	{
		char tempBuff[1024] = "";

		strcpy(tempBuff , Buff);
		tempBuff[LenMax]=0;
		wynik = TF_WritePkt_Small(tempBuff, (unsigned char)LenMax);

		if(wynik >= 0)
		{
			strcpy(tempBuff, Buff+LenMax);
			wynik = TF_WritePkt_Small(tempBuff, (unsigned char)(Len-LenMax));
		}
	}
	else
	{
		wynik = TF_WritePkt_Small(Buff, (unsigned char)Len);
	}

	return wynik;


	//return TF_WritePkt_Small(Buff, (unsigned char)Len);
}
int FileReceiveN(int CommId, int FileHandle)
{
	//int CommId;
	int DataLen, i, err_desc;
	char Buff[512];
	char key;
	int oDemoLicz = 0;

	key = getchar();
	if (Klawisz(key, (char*)"ESC"))
	{
		return -1;
	}

	//CommId = TF_OpenPort(PortType, PortSettings);
	if(CommId < 0)
	{
		//return 0;
		return -33;
	}

	TF_InitProt(CommId);
	NoBytes=wait=0;

	if((err_desc = TF_ReadPkt(Buff)) < 0)
	{
		return err_desc;
	}
	sscanf(Buff, "%ld", &NoBytes);
	file_size = NoBytes; 

	if((!odbieramy_DB_IDX && file_size >= (free_memory() - (2*MEM_TMP + MEM_DOC_TMP))) || 
		(odbieramy_DB_IDX && file_size >= (free_memory() - (2*MEM_TMP + MEM_DOC_TMP+(NoBytes*0.21)))) ||
		NoBytes/recLen > 99999)
	{
		return -10;
	}

	#if (MACHINETYPE == 8000 || MACHINETYPE == 8300)
		if (Konfiguracja.TransmisjaPort==COMM_IrDA)
			TF_IrDA();
		else
			TF_ANY();
	#endif

	Progress = 0;

	while((DataLen = TF_ReadPkt(Buff)) > 0)
	{
		if(write(FileHandle, Buff, DataLen) != DataLen)
		{
			return -11;
		}

		_KeepAlive__();			//zapobiega automatycznemu wy³¹czeniu
		Progress += DataLen;
		DispProgress(0);
		TF_ShowSandGlass();
	}

	if(DataLen < 0)
	{
		TF_EndOfTx();
		TF_ClosePort(CommId);
		return DataLen;
	}

	TF_EndOfTx();
	TF_ClosePort(CommId);
	return 1;
}
int TF_RewindFile(int FileHandle)
{
	if(lseek(FileHandle, 0, 1) < 0)
		return -1;

	return FileHandle;
}
int TF_ReadFileLine(int FileHandle, char *bufor)
{
	int bytesInLine = 0;
	char ch[2];
	int ok=FALSE;

	while(read(FileHandle, ch, 1))
	{
		ok=FALSE;
		if(!memcmp(ch, "\n", 1)) //\r\n
		{
			bufor[--bytesInLine] = '\0';
			ok=TRUE;
			break;
		}
		else
		{
			strncpy(bufor+(bytesInLine++), ch, 1);
		}
	}

	return bytesInLine;
}
int ReceiveCom (BYTE *szStr, int nTransType)
{
	int   i=0;
	char  c;
//test_s((char*)"ReceiveCom", (char*)"1");

	while (TRUE)
	{
		i = 0;
		c='\0';
		while (i < 10)
		{
			if (Klawisz(getchar(), (char*)"ESC"))
			{
				close_com (TF_PortType);
				return -1;
			}

			if (read_com (TF_PortType, &c))
				Dane [i++] = c;

			if ((c == '\r') || (c == '\n'))
				break;
		}
		Dane [i] = '\0';

		if ((i < 2) && ((Dane [0] == '\r') || (Dane [0] == '\n')))
			continue;

		if (!strcmp ((char *)Dane, (char *)szStr))
		{
			write_com (TF_PortType, ACK);
			while (!com_eot (TF_PortType));
		}
		else
		{
			write_com (TF_PortType, NAK);
			while (!com_eot (TF_PortType));
			continue;
		}

		return TRUE;
	}
}
void WriteCOM (char *szStr, int nTransType)
{
	write_com (TF_PortType, szStr);
	while (!com_eot (TF_PortType));
}
//==================================================================================
void KonwersjaExportRekordu(void)
{
	int i;
	int wynik;
	DOKUMENT_WYJSCIOWY_POZYCJA	DokumentWyjsciowyPozycja;

	strcpy(DokumentWyjsciowyPozycja.DokumentId, "");
	strcpy(DokumentWyjsciowyPozycja.DokumentIdRodzaj, "");
	strcpy(DokumentWyjsciowyPozycja.RodzajDokOpis, "");
	strcpy(DokumentWyjsciowyPozycja.DokumentIdKontr, "");
	strcpy(DokumentWyjsciowyPozycja.KontrahentOpis, "");
	strcpy(DokumentWyjsciowyPozycja.DokumentKod, "");
	strcpy(DokumentWyjsciowyPozycja.TowarNazwa, "");
	strcpy(DokumentWyjsciowyPozycja.TowarCena, "");
	strcpy(DokumentWyjsciowyPozycja.TowarStan, "");
	strcpy(DokumentWyjsciowyPozycja.TowarJm, "");
	strcpy(DokumentWyjsciowyPozycja.TowarOpis, "");
	strcpy(DokumentWyjsciowyPozycja.DokumentIlosc, "");
	strcpy(DokumentWyjsciowyPozycja.DokumentOpisDok, "");
	strcpy(DokumentWyjsciowyPozycja.DokumentOpisPoz, "");
	strcpy(DokumentWyjsciowyPozycja.DokumentData, "");

	// Ustawienie separatora
	DokumentWyjsciowyPozycja.Separator = (char)Konfiguracja.DokumentWySeparator; 

	// pobranie id dokumentu
	if(Konfiguracja.DokumentWyDokumentId!=0)
	{
		if(Ekran.DokumentId == TAK)
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentId, (char*)Dane+Konfiguracja.BazaLokDokumentId);
			DokumentWyjsciowyPozycja.DokumentId[Konfiguracja.BazaDokumentId] = 0;
		}
		else
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentId, "");
		}
	}

	// pobranie id rodzaju dokumentu
	if(Konfiguracja.DokumentWyDokumentIdRodzaj!=0)
	{
		if(Ekran.RodzajDokumentuId == TAK)
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentIdRodzaj, (char*)Dane+Konfiguracja.BazaLokDokumentIdRodzaj);
			DokumentWyjsciowyPozycja.DokumentIdRodzaj[Konfiguracja.BazaRodzajDokumentuId] = 0;
		}
		else
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentIdRodzaj, "");
		}
	}

	// pobranie opisu rodzaju dokumentu
	if(Konfiguracja.DokumentWyRodzajDokOpis!=0)
	{
		if(Ekran.RodzajDokumentuId == TAK)
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentIdRodzaj, (char*)Dane+Konfiguracja.BazaLokDokumentIdRodzaj);
			DokumentWyjsciowyPozycja.DokumentIdRodzaj[Konfiguracja.BazaRodzajDokumentuId] = 0;
			
			if( has_member(PlikRodzajeDokumentow, 1, (char *)DokumentWyjsciowyPozycja.DokumentIdRodzaj) )
			{
				get_member(PlikRodzajeDokumentow, 1, (char *)Bufor);

				strcpy(DokumentWyjsciowyPozycja.RodzajDokOpis, (char*)Bufor+Konfiguracja.BazaLokRodzajDokumentuOpis);
				DokumentWyjsciowyPozycja.RodzajDokOpis[Konfiguracja.BazaRodzajDokumentuOpis] = 0;
			}
		}
		else
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentIdRodzaj, "");
			strcpy(DokumentWyjsciowyPozycja.RodzajDokOpis, "");
		}
	}

	// pobranie id kontrahenta
	if(Konfiguracja.DokumentWyDokumentIdKontr!=0)
	{
		if(Ekran.KontrahentId == TAK)
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentIdKontr, (char*)Dane+Konfiguracja.BazaLokDokumentIdKontrahent);
			DokumentWyjsciowyPozycja.DokumentIdKontr[Konfiguracja.BazaKontrahentId] = 0;
		}
		else
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentIdKontr, "");
		}
	}

	// pobranie opisu kontrahenta
	if(Konfiguracja.DokumentWyKontrahentOpis!=0)
	{
		if(Ekran.KontrahentId == TAK)
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentIdKontr, (char*)Dane+Konfiguracja.BazaLokDokumentIdKontrahent);
			DokumentWyjsciowyPozycja.DokumentIdKontr[Konfiguracja.BazaKontrahentId] = 0;

			if( has_member(PlikKontrahenci, 1, (char *)DokumentWyjsciowyPozycja.DokumentIdKontr) )
			{
				get_member(PlikKontrahenci, 1, (char *)Bufor);

				strcpy(DokumentWyjsciowyPozycja.KontrahentOpis, (char*)Bufor+Konfiguracja.BazaLokKontrahentOpis);
				DokumentWyjsciowyPozycja.KontrahentOpis[Konfiguracja.BazaKontrahentOpis] = 0;
			}
		}
		else
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentIdKontr, "");
			strcpy(DokumentWyjsciowyPozycja.KontrahentOpis, "");
		}
	}

	// pobranie kodu kreskowego
	strcpy(DokumentWyjsciowyPozycja.DokumentKod, (char*)Dane+Konfiguracja.BazaLokDokumentKod);
	DokumentWyjsciowyPozycja.DokumentKod[Konfiguracja.BazaTowarKod] = 0;

	if((Konfiguracja.DokumentWyTowarNazwa!=0) || (Konfiguracja.DokumentWyTowarCena!=0) || (Konfiguracja.DokumentWyTowarStan!=0) || (Konfiguracja.DokumentWyTowarJm!=0) || (Konfiguracja.DokumentWyTowarOpis!=0))
	{
		if( has_member(PlikTowary, 1, (char *)DokumentWyjsciowyPozycja.DokumentKod) )
		{
			get_member(PlikTowary, 1, (char *)Bufor);
		}
		else
		{
			for (i=0; i<=Plik[TOWARY].Dlugosc; i++)
			{
				Bufor[i]=' '; 
			}
			Bufor[Plik[TOWARY].Dlugosc]=0;
		}

		// pobranie nazwy towaru
		if(Konfiguracja.DokumentWyTowarNazwa!=0)
		{
			if(Ekran.TowarNazwa == NIE)
			{
				strcpy(DokumentWyjsciowyPozycja.TowarNazwa, "");
			}
			else
			{
				strcpy(DokumentWyjsciowyPozycja.TowarNazwa, (char*)Bufor+Konfiguracja.BazaLokTowarNazwa);
				DokumentWyjsciowyPozycja.TowarNazwa[Konfiguracja.BazaTowarNazwa] = 0;
			}
		}

		// pobranie ceny towaru
		if(Konfiguracja.DokumentWyTowarCena!=0)
		{
			if(Ekran.TowarCena == NIE)
			{
				strcpy(DokumentWyjsciowyPozycja.TowarCena, "");
			}
			else
			{
				strcpy(DokumentWyjsciowyPozycja.TowarCena, (char*)Bufor+Konfiguracja.BazaLokTowarCena);
				DokumentWyjsciowyPozycja.TowarCena[Konfiguracja.BazaTowarCena] = 0;
			}
		}

		// pobranie stanu towaru
		if(Konfiguracja.DokumentWyTowarStan!=0)
		{
			if(Ekran.TowarStan == NIE)
			{
				strcpy(DokumentWyjsciowyPozycja.TowarStan, "");
			}
			else
			{
				strcpy(DokumentWyjsciowyPozycja.TowarStan, (char*)Bufor+Konfiguracja.BazaLokTowarStan);
				DokumentWyjsciowyPozycja.TowarStan[Konfiguracja.BazaTowarStan] = 0;
			}
		}

		// pobraniejednostki miary
		if(Konfiguracja.DokumentWyTowarJm!=0)
		{
			if(Ekran.TowarJm == NIE)
			{
				strcpy(DokumentWyjsciowyPozycja.TowarJm, "");
			}
			else
			{
				strcpy(DokumentWyjsciowyPozycja.TowarJm, (char*)Bufor+Konfiguracja.BazaLokTowarJm);
				DokumentWyjsciowyPozycja.TowarJm[Konfiguracja.BazaTowarJm] = 0;
			}
		}

		// pobranie opisu towaru
		if(Konfiguracja.DokumentWyTowarOpis!=0)
		{
			if(Ekran.TowarOpis == NIE)
			{
				strcpy(DokumentWyjsciowyPozycja.TowarOpis, "");
			}
			else
			{
				strcpy(DokumentWyjsciowyPozycja.TowarOpis, (char*)Bufor+Konfiguracja.BazaLokTowarOpis);
				DokumentWyjsciowyPozycja.TowarOpis[Konfiguracja.BazaTowarOpis] = 0;
			}
		}
	}

	// pobranie iloœci wprowadzonej na dokument
	if(Konfiguracja.DokumentWyDokumentIlosc!=0)
	{
		strcpy(DokumentWyjsciowyPozycja.DokumentIlosc, (char*)Dane+Konfiguracja.BazaLokDokumentIlosc);
		DokumentWyjsciowyPozycja.DokumentIlosc[Konfiguracja.BazaDokumentIlosc] = 0;
	}

	// pobranie opisu dokumentu
	if(Konfiguracja.DokumentWyDokumentOpisDok!=0)
	{
		if(Ekran.DokumentOpisDok == NIE)
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentOpisDok, "");
		}
		else
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentOpisDok, (char*)Dane+Konfiguracja.BazaLokDokumentOpisDok);
			DokumentWyjsciowyPozycja.DokumentOpisDok[Konfiguracja.BazaDokumentOpisDok] = 0;
		}
	}

	// pobranie opisu pozycji
	if(Konfiguracja.DokumentWyDokumentOpisPoz!=0)
	{
		if(Ekran.DokumentOpisPoz == NIE)
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentOpisPoz, "");
		}
		else
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentOpisPoz, (char*)Dane+Konfiguracja.BazaLokDokumentOpisPoz);
			DokumentWyjsciowyPozycja.DokumentOpisPoz[Konfiguracja.BazaDokumentOpisPoz] = 0;
		}
	}

	// pobranie daty utworzenia dokumentu
	if(Konfiguracja.DokumentWyDokumentData!=0)
	{
		if(Ekran.DokumentData == NIE)
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentData, "");
		}
		else
		{
			strcpy(DokumentWyjsciowyPozycja.DokumentData, (char*)Dane+Konfiguracja.BazaLokDokumentData);
			DokumentWyjsciowyPozycja.DokumentData[Konfiguracja.BazaDokumentData] = 0;
		}
	}

	KonwersjaRekorduDokWyj(DokumentWyjsciowyPozycja, (char*)Dane);
}
void KonwersjaRekorduDokWyj(DOKUMENT_WYJSCIOWY_POZYCJA RekordDokumentu, char* wynik)
{
	BYTE i = 0;
	BYTE licznik = 0;
	BYTE PoleWypelnione = FALSE;
	BYTE MaxSizeRecord=0;
	char Tablica[20+1][64];

	for (i=1; i<=MAX_ROZMIAR_REKORDU; i++)
	{
		PoleWypelnione = FALSE;

		if (Konfiguracja.DokumentWyDokumentId==i)
		{
			strcpy(Tablica[licznik++], RekordDokumentu.DokumentId);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyDokumentIdKontr==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.DokumentIdKontr);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyKontrahentOpis==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.KontrahentOpis);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyDokumentIdRodzaj==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.DokumentIdRodzaj);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyRodzajDokOpis==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.RodzajDokOpis);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyDokumentKod==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.DokumentKod);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyTowarNazwa==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.TowarNazwa);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyTowarCena==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.TowarCena);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyTowarStan==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.TowarStan);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyTowarJm==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.TowarJm);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyTowarOpis==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.TowarOpis);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyDokumentIlosc==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.DokumentIlosc);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyDokumentOpisDok==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.DokumentOpisDok);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyDokumentOpisPoz==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.DokumentOpisPoz);
			PoleWypelnione = TRUE;
		}
		if (Konfiguracja.DokumentWyDokumentData==i) 
		{
			strcpy(Tablica[licznik++], RekordDokumentu.DokumentData);
			PoleWypelnione = TRUE;
		}

		if(!PoleWypelnione)
			strcpy(Tablica[licznik++], "");
		else
			if(MaxSizeRecord<i) MaxSizeRecord = i;
	}

	ArrayToRecord(Tablica, MaxSizeRecord, RekordDokumentu.Separator, wynik);
}
void ArrayToRecord(char (*Array)[64], BYTE SizeArray, char Separator, char* wynik)
{
	int i=0;
	char record[512] = {'\0'};
	char separator[2];
	sprintf(separator, "%c", Separator);

	strcpy(record, "");

	for(i=0; i<SizeArray; i++)
	{
		if(i!=0) strcat(record, separator);

		strcat(record, *(Array+i));
	}

	strcpy(wynik, record);
}
void KonwersjaExportTowar(void)
{
	int i;

	char szTowKod[512] = "";
	char szTowNazwa[512] = "";
	char szTowCena[512] = "";
	char szTowStan[512] = "";
	char szTowJm[512] = "";
	char szTowOpis[512] = "";

	char szSeparator[8] = "";

	// Ustawienie separatora
	szSeparator[0] = Konfiguracja.DokumentWeTowarSep; 
	szSeparator[1] = 0;

	strcpy(szTowKod, (char*)Dane+Konfiguracja.BazaLokTowarKod);
	szTowKod[Konfiguracja.BazaTowarKod]=0;

	if(Ekran.TowarNazwa == NIE)
	{
		strcpy(szTowNazwa, "");
	}
	else
	{
		strcpy(szTowNazwa, (char*)Dane+Konfiguracja.BazaLokTowarNazwa);
		szTowNazwa[Konfiguracja.BazaTowarNazwa]=0;
	}

	if(Ekran.TowarCena == NIE)
	{
		strcpy(szTowCena, "");
	}
	else
	{
		strcpy(szTowCena, (char*)Dane+Konfiguracja.BazaLokTowarCena);
		szTowCena[Konfiguracja.BazaTowarCena]=0;
	}

	if(Ekran.TowarStan == NIE)
	{
		strcpy(szTowStan, "");
	}
	else
	{
		strcpy(szTowStan, (char*)Dane+Konfiguracja.BazaLokTowarStan);
		szTowStan[Konfiguracja.BazaTowarStan]=0;
	}

	if(Ekran.TowarJm == NIE)
	{
		strcpy(szTowJm, "");
	}
	else
	{
		strcpy(szTowJm, (char*)Dane+Konfiguracja.BazaLokTowarJm);
		szTowJm[Konfiguracja.BazaTowarJm]=0;
	}

	if(Ekran.TowarOpis == NIE)
	{
		strcpy(szTowOpis, "");
	}
	else
	{
		strcpy(szTowOpis, (char*)Dane+Konfiguracja.BazaLokTowarOpis);
		szTowOpis[Konfiguracja.BazaTowarOpis]=0;
	}

	strcpy((char*)Dane,"");

	for (i=1; i<=20; i++)
	{
		if (Konfiguracja.DokumentWeTowarKod==i)  { strcat((char*)Dane, szTowKod); }
		if (Konfiguracja.DokumentWeTowarNazwa==i)  { strcat((char*)Dane, szTowNazwa); }
		if (Konfiguracja.DokumentWeTowarCena==i)  { strcat((char*)Dane, szTowCena); }
		if (Konfiguracja.DokumentWeTowarStan==i)  { strcat((char*)Dane, szTowStan); }
		if (Konfiguracja.DokumentWeTowarJm==i)  { strcat((char*)Dane, szTowJm); }
		if (Konfiguracja.DokumentWeTowarOpis==i) { strcat((char*)Dane, szTowOpis); }

		strcat((char*)Dane, szSeparator);
	}

	// obciecie separatorów od koñca
	for (i=strlen((char*)Dane)-1; i>=0; --i)
		if (Dane[i]==szSeparator[0])
			Dane[i]=0; 
		else
			break;

	//strcpy(Dane, DaneDoExportu);
	i=0;
}
void KonwersjaExportKontrahent(void)
{
	int i;

	char szKonId[512] = "";
	char szKonOpis[512] = "";

	char szSeparator[8] = "";

	// Ustawienie separatora
	szSeparator[0] = Konfiguracja.DokumentWeKontrahentSep; 
	szSeparator[1] = 0;

	strcpy(szKonId, (char*)Dane+Konfiguracja.BazaLokKontrahentId);
	szKonId[Konfiguracja.BazaKontrahentId]=0;
	strcpy(szKonOpis, (char*)Dane+Konfiguracja.BazaLokKontrahentOpis);
	szKonOpis[Konfiguracja.BazaKontrahentOpis]=0;

	strcpy((char*)Dane,"");

	for (i=1; i<=20; i++)
	{
		if (Konfiguracja.DokumentWeKontrahentId==i)  { strcat((char*)Dane, szKonId); }
		if (Konfiguracja.DokumentWeKontrahentOpis==i)  { strcat((char*)Dane, szKonOpis); }

		strcat((char*)Dane, szSeparator);
	}

	// obciecie separatorów od koñca
	for (i=strlen((char*)Dane)-1; i>=0; --i)
		if (Dane[i]==szSeparator[0])
			Dane[i]=0; 
		else
			break;

	//strcpy(Dane, DaneDoExportu);
	i=0;
}
void KonwersjaExportRodzajDok(void)
{
	int i;

	char szRDokId[512] = "";
	char szRDokOpis[512] = "";

	char szSeparator[8] = "";

	// Ustawienie separatora
	szSeparator[0] = Konfiguracja.DokumentWeRodzajDokumentuSep; 
	szSeparator[1] = 0;

	strcpy(szRDokId, (char*)Dane+Konfiguracja.BazaLokRodzajDokumentuId);
	szRDokId[Konfiguracja.BazaRodzajDokumentuId]=0;

	strcpy(szRDokOpis, (char*)Dane+Konfiguracja.BazaLokRodzajDokumentuOpis);
	szRDokOpis[Konfiguracja.BazaRodzajDokumentuOpis]=0;

	strcpy((char*)Dane,"");

	for (i=1; i<=20; i++)
	{
		if (Konfiguracja.DokumentWeRodzajDokumentuId==i)  { strcat((char*)Dane, szRDokId); }
		if (Konfiguracja.DokumentWeRodzajDokumentuOpis==i)  { strcat((char*)Dane, szRDokOpis); }

		strcat((char*)Dane, szSeparator);
	}

	// obciecie separatorów od koñca
	for (i=strlen((char*)Dane)-1; i>=0; --i)
		if (Dane[i]==szSeparator[0])
			Dane[i]=0; 
		else
			break;

	//strcpy(Dane, DaneDoExportu);
	i=0;
}
void KonwersjaImport(int plik)
{
	switch(plik)
	{
		case TOWARY: KonwersjaImportTowary(); break;
		case DOKUMENTY: KonwersjaImportDokumenty(); break;
		case KONTRAHENCI: KonwersjaImportKontrahenci(); break;
		case RODZAJEDOK: KonwersjaImportRodzajeDok(); break;
		case KOMPLETACJA: KonwersjaImportKompletacja(); break;
	}
	KonwersjaWyrownanie(plik);
}
void KonwersjaImportTowary(void)
{
	int i;
	int a=0,b=0,c=0,d=0,e=0,f=0;
	int nNR;
	BYTE szNA[512]="";
	BYTE szKK[512]="";
	BYTE szCE[512]="";
	BYTE szST[512]="";
	BYTE szJM[512]="";
	BYTE szOP[512]="";
	BYTE szSE[8]="";

	// separator wew
	szSE[0]=Konfiguracja.BazaSeparator; szSE[1]=0;


	// w przypadku gdy separator jest inny ni¿ wew nalezy usun¹æ wew z treœci rekordu , -> .
	if (Konfiguracja.DokumentWeTowarSep == Konfiguracja.BazaSeparator)
	{
		szSE[0]=30; szSE[1]=0;

		for (i=0; i<strlen((char*)Dane); i++)
		{
			if (Dane[i]==Konfiguracja.BazaSeparator)
			{
				Dane[i]=30;
			}
		}
	}



	nNR=1;
	for (i=0; i<strlen((char*)Dane); i++)
	{
		if (Dane[i]==Konfiguracja.DokumentWeTowarSep){
			nNR++;
		}
		else
		{
			// pobranie pola KK
			if (nNR==Konfiguracja.DokumentWeTowarKod)	
				if(b!=0 || Dane[i]!=' ')
					szKK[b++]=Dane[i];
			else			szKK[b]=0;

			// pobranie pola Nazwa
			if (nNR==Konfiguracja.DokumentWeTowarNazwa)	
				if(a!=0 || Dane[i]!=' ')
					szNA[a++]=Dane[i];
			else			szNA[a]=0;

			// pobranie pola CE - cena
			if (nNR==Konfiguracja.DokumentWeTowarCena)	
				if(c!=0 || Dane[i]!=' ')
					szCE[c++]=Dane[i];
			else			szCE[c]=0;

			// pobranie pola ST - Stan
			if (nNR==Konfiguracja.DokumentWeTowarStan)	
				if(d!=0 || Dane[i]!=' ')
					szST[d++]=Dane[i];
			else			szST[d]=0;

			// pobranie pola JM
			if (nNR==Konfiguracja.DokumentWeTowarJm)	
				if(e!=0 || Dane[i]!=' ')
					szJM[e++]=Dane[i];
			else			szJM[e]=0;

			// pobranie pola Opis
			if (nNR==Konfiguracja.DokumentWeTowarOpis)	
				if(f!=0 || Dane[i]!=' ')
					szOP[f++]=Dane[i];
			else			szOP[f]=0;
		}
		Dane[i]=' ';
	}

	szNA[a]=0;
	szKK[b]=0;
	szCE[c]=0;
	szST[d]=0;
	szJM[e]=0;
	szOP[f]=0;


	strcpy((char*)Dane, (char*)szNA);
	strcat((char*)Dane, (char*)szSE);
	strcat((char*)Dane, (char*)szKK);
	strcat((char*)Dane, (char*)szSE);
	strcat((char*)Dane, (char*)szCE);
	strcat((char*)Dane, (char*)szSE);
	strcat((char*)Dane, (char*)szST);
	strcat((char*)Dane, (char*)szSE);
	strcat((char*)Dane, (char*)szJM);
	strcat((char*)Dane, (char*)szSE);
	strcat((char*)Dane, (char*)szOP);
}
void KonwersjaImportDokumenty(void)
{
	int i;
	int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
	int nNR;
	BYTE szID[512]="";
	BYTE szRO[512]="";
	BYTE szKO[512]="";
	BYTE szKK[512]="";
	BYTE szIL[512]="";
	BYTE szOPD[512]="";
	BYTE szOPP[512]="";
	BYTE szDA[512]="";
	BYTE szSE[8]="";

	
	// separator wew
	szSE[0]=Konfiguracja.BazaSeparator; szSE[1]=0;

	// w przypadku gdy separator jest inny ni¿ wew nalezy usun¹æ wew z treœci rekordu , -> .
	if (Konfiguracja.DokumentWeDokumentSep==Konfiguracja.BazaSeparator)
	{
		szSE[0]=30; szSE[1]=0;

		for (i=0; i<strlen((char*)Dane); i++)
		{
			if (Dane[i]==Konfiguracja.BazaSeparator)
			{
				Dane[i]=30;
			}
		}
	}

	nNR=1;
	for (i=0; i<strlen((char*)Dane); i++)
	{
		if (Dane[i]==Konfiguracja.DokumentWeDokumentSep){
			nNR++;
		}
		else
		{
			// pobranie pola Id Dokumentu
			if (nNR==Konfiguracja.DokumentWeDokumentId)	
				if(a!=0 || Dane[i]!=' '){
					szID[a++]=Dane[i];
					szID[a]=0;
				}
			else			szID[a]=0;

			// pobranie pola Id Rodzaju dokumentu
			if (nNR==Konfiguracja.DokumentWeDokumentIdRodzaj)	
				if(b!=0 || Dane[i]!=' '){
					szRO[b++]=Dane[i];
					szRO[b]=0;
				}
			else			szRO[b]=0;

			// pobranie pola Id Kontrahenta
			if (nNR==Konfiguracja.DokumentWeDokumentIdKontr)	
				if(c!=0 || Dane[i]!=' '){
					szKO[c++]=Dane[i];
					szKO[c]=0;
				}
			else			szKO[c]=0;

			// pobranie pola Kod kreskowy
			if (nNR==Konfiguracja.DokumentWeDokumentKod)	
				if(d!=0 || Dane[i]!=' '){
					szKK[d++]=Dane[i];
					szKK[d]=0;
				}
			else			szKK[d]=0;

			// pobranie pola Iloœæ
			if (nNR==Konfiguracja.DokumentWeDokumentIlosc)	
				if(e!=0 || Dane[i]!=' '){
					szIL[e++]=Dane[i];
					szIL[e]=0;
				}
			else			szIL[e]=0;

			// pobranie pola Opis dokumentu
			if (nNR==Konfiguracja.DokumentWeDokumentOpisDok)	
				if(f!=0 || Dane[i]!=' '){
					szOPD[f++]=Dane[i];
					szOPD[f]=0;
				}
			else			szOPD[f]=0;

			// pobranie pola Opis pozycji
			if (nNR==Konfiguracja.DokumentWeDokumentOpisPoz)	
				if(g!=0 || Dane[i]!=' '){
					szOPP[g++]=Dane[i];
					szOPP[g]=0;
				}
			else			szOPP[g]=0;

			// pobranie pola Data
			if (nNR==Konfiguracja.DokumentWeDokumentData)	
				if(h!=0 || Dane[i]!=' '){
					szDA[h++]=Dane[i];
					szDA[h]=0;
				}
			else			szDA[h]=0;
		}
		Dane[i]=' ';
	}

	szID[a]=0;
	szRO[b]=0;
	szKO[c]=0;
	szKK[d]=0;
	szIL[e]=0;
	szOPD[f]=0;
	szOPP[g]=0;
	szDA[h]=0;

	strcpy((char*)Dane,(char*)szID);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane,(char*)szRO);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane,(char*)szKO);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane,(char*)szKK);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane,(char*)szIL);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane,(char*)szOPD);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane,(char*)szOPP);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane, (char*)szDA);
}
void KonwersjaImportKontrahenci(void)
{
	int i;
	int a=0,b=0;
	int nNR;
	BYTE szID[512]="";
	BYTE szOP[512]="";
	BYTE szSE[8]="";

	// separator wew 
	szSE[0]=Konfiguracja.BazaSeparator; szSE[1]=0;

	// w przypadku gdy separator jest inny ni¿ wew nalezy usun¹æ wew z treœci rekordu , -> .
	if (Konfiguracja.DokumentWeKontrahentSep==Konfiguracja.BazaSeparator)
	{
		for (i=0; i<strlen((char*)Dane); i++)
		{
			szSE[0]=30; szSE[1]=0;

			if (Dane[i]==Konfiguracja.BazaSeparator)
			{
				Dane[i]=30;
			}
		}
	}

	nNR=1;
	for (i=0; i<strlen((char*)Dane); i++)
	{
		if (Dane[i]==Konfiguracja.DokumentWeKontrahentSep){
			nNR++;
		}
		else
		{
			// pobranie pola Id Kontrahenta
			if (nNR==Konfiguracja.DokumentWeKontrahentId)	
				if(a!=0 || Dane[i]!=' '){
					szID[a++]=Dane[i];
					szID[a]=0;
				}
			else			szID[a]=0;

			// pobranie pola Opis kontrahenta
			if (nNR==Konfiguracja.DokumentWeKontrahentOpis)	
				if(b!=0 || Dane[i]!=' '){
					szOP[b++]=Dane[i];
					szOP[b]=0;
				}
			else			szOP[b]=0;
		}
		Dane[i]=' ';
	}

	szID[a]=0;
	szOP[b]=0;

	strcpy((char*)Dane,(char*)szID);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane,(char*)szOP);
}
void KonwersjaImportRodzajeDok(void)
{
	int i;
	int a=0,b=0,c=0,d=0,e=0,f=0;
	int nNR;
	BYTE szID[512]="";
	BYTE szOP[512]="";
	BYTE szSE[8]="";

	// separator wew
	szSE[0]=Konfiguracja.BazaSeparator; szSE[1]=0;

	// w przypadku gdy separator jest inny ni¿ wew nalezy usun¹æ wew z treœci rekordu , -> .
	if (Konfiguracja.DokumentWeRodzajDokumentuSep==Konfiguracja.BazaSeparator)
	{
		for (i=0; i<strlen((char*)Dane); i++)
		{
			szSE[0]=30; szSE[1]=0;

			if (Dane[i]==Konfiguracja.BazaSeparator)
			{
				Dane[i]=30;
			}
		}
	}

	nNR=1;
	for (i=0; i<strlen((char*)Dane); i++)
	{
		if (Dane[i]==Konfiguracja.DokumentWeRodzajDokumentuSep){
			nNR++;
		}
		else
		{
			// pobranie pola Id Rodzaj dokumentu
			if (nNR==Konfiguracja.DokumentWeRodzajDokumentuId)	
				if(a!=0 || Dane[i]!=' '){
					szID[a++]=Dane[i];
					szID[a]=0;
				}
			else szID[a]=0;

			// pobranie pola Opis rodzaju dokumnetu
			if (nNR==Konfiguracja.DokumentWeRodzajDokumentuOpis)	
				if(b!=0 || Dane[i]!=' '){
					szOP[b++]=Dane[i];
					szOP[b]=0;
				}
			else szOP[b]=0;
		}
		Dane[i]=' ';
	}

	szID[a]=0;
	szOP[b]=0;

	strcpy((char*)Dane,(char*)szID);
	strcat((char*)Dane,(char*)szSE);
	strcat((char*)Dane,(char*)szOP);
}
void KonwersjaImportKompletacja(void)
{
	int i;
	int a1=0, a2=0, a3=0, a4=0, a5=0, a6=0, a7=0, a8=0, a9=0, a10=0, a11=0, a12=0, a13=0;
	int nNR = 1;
	char sz1[256]={'\0'};
	char sz21[256]={'\0'};
	char sz3[256]={'\0'};
	char sz4[256]={'\0'};
	char sz5[256]={'\0'};
	char sz6[256]={'\0'};
	char sz7[256]={'\0'};
	char sz8[256]={'\0'};
	char sz9[256]={'\0'};
	char sz10[256]={'\0'};
	char sz11[256]={'\0'};
	char sz12[256]={'\0'};
	char sz13[256]={'\0'};
	char szSE[8]={'\0'};
	char DaneTmp[2048]={'\0'};

	// separator wew 44
	szSE[0]=(char)Konfiguracja.BazaKompletacjaSeparator; szSE[1]=0;


	strcpy(DaneTmp, (char*)Dane);

	for (i=0; i<strlen(DaneTmp); ++i)
	{
		if (DaneTmp[i]==Konfiguracja.BazaKompletacjaSeparator){
			nNR++;
		}
		else
		{
			if (nNR==1) sz1[a1++]=DaneTmp[i];
			else			sz1[a1]=0;

			if (nNR==2) sz21[a2++]=DaneTmp[i];
			else			sz21[a2]=0;

			if (nNR==3) sz3[a3++]=DaneTmp[i];
			else			sz3[a3]=0;

			if (nNR==4) sz4[a4++]=DaneTmp[i];
			else			sz4[a4]=0;

			if (nNR==5) sz5[a5++]=DaneTmp[i];
			else			sz5[a5]=0;

			if (nNR==6) sz6[a6++]=DaneTmp[i];
			else			sz6[a6]=0;

			if (nNR==7) sz7[a7++]=DaneTmp[i];
			else			sz7[a7]=0;

			if (nNR==8) sz8[a8++]=DaneTmp[i];
			else			sz8[a8]=0;

			if (nNR==9) sz9[a9++]=DaneTmp[i];
			else			sz9[a9]=0;

			if (nNR==10) sz10[a10++]=DaneTmp[i];
			else			sz10[a10]=0;

			if (nNR==11) sz11[a11++]=DaneTmp[i];
			else			sz11[a11]=0;

			if (nNR==12) sz12[a12++]=DaneTmp[i];
			else			sz12[a12]=0;
		}
	}

	sz1[a1]=0;
	sz21[a2]=0;
	sz3[a3]=0;
	sz4[a4]=0;
	sz5[a5]=0;
	sz6[a6]=0;
	sz7[a7]=0;
	sz8[a8]=0;
	sz9[a9]=0;
	sz10[a10]=0;
	sz11[a11]=0;
	sz12[a12]=0;

	if(strstr(sz7, ".")){
		for(i=strlen(sz7)-1; i>0; i--){
			if((sz7[i]=='0') || (sz7[i]==' '))
				sz7[i]=' ';
			else if ( sz7[i] == '.' ){
				sz7[i]=' ';
				break;
			}
			else
				break; 
		}
	}

	if(strstr(sz8, ".")){
		for(i=strlen(sz8)-1; i>0; i--){
			if((sz8[i]=='0') || (sz8[i]==' '))
				sz8[i]=' ';
			else if ( sz8[i] == '.' ){
				sz8[i]=' ';
				break;
			}
			else
				break; 
		}
	}

	if(strstr(sz12, ".")){
		for(i=strlen(sz12)-1; i>0; i--){
			if((sz12[i]=='0') || (sz12[i]==' '))
				sz12[i]=' ';
			else if ( sz12[i] == '.' ){
				sz12[i]=' ';
				break;
			}
			else
				break; 
		}
	}

//0  nNumerDok		1
//1  nKodK			2
//2  nTowarN		3
//3  nLp			4
//4  nId			5
//5  nCenaBr		6
//6  nCenaNe		7
//7	 nIloscJm		8
//8  nIloscKom		9
//9  nIlosc			12
//10 nStatus		13
//11 nTowarSym		10
//12 nTypDok		11

	strcpy(DaneTmp, sz1);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz21);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz3);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz4);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz5);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz6);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz7);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz8);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz9);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz12);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, "0");
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz10);
	strcat(DaneTmp, szSE);
	strcat(DaneTmp, sz11);

	strcpy((char*)Dane, DaneTmp);
}
void KonwersjaWyrownanie(int plik)
{
	BYTE c=0, d=0;
	int n = 0;
	int z = 0;
	int k = 0;
	int x = 0;

	d = Plik [plik].Separator;        // lookup field properties : use delimiter

	for (x=0; x<strlen((char *)Dane); x++)
	{
		c = Dane [x];
		if (c == d)									// delimiter
		{
			while (n++ < Plik [plik].DlugoscRekordu [z])
				Bufor [k++] = ' ';					// add space

			if (++z < Plik [plik].PolaIlosc)
				Bufor [k++] = d;					// add delimiter

			n = 0;
		}
		else
		{
			if (n < Plik [plik].DlugoscRekordu [z])
			{
				Bufor [k++] = c;                // add data
				n++;
			}
		}
	}

	while (n < Plik [plik].DlugoscRekordu [z])
	{
		Bufor [k++] = ' ';                      // add space
		n++;
	}

	Bufor [k] = '\0';
	strcpy ((char *)Dane, (char *)Bufor);
}
//==================================================================================
int CreateKonfig(int FileName)
{
	int i=0, ii=0, ok=0;
	char tmp_buff[512];
	int DataLen=0;

	sprintf(tmp_buff,"#USTAWIENIA\r\n");

	DataLen = strlen(tmp_buff);
	tmp_buff[DataLen] = 0;

	if(write(FileName, tmp_buff, DataLen) != DataLen)
	{
		return FALSE;
	}

	for (i=0; i<UstawieniaIlosc; i++)
	{
		tmp_buff[0] = 0;
		ok=0;

		if( KonfiguracjaUst[i].grupa == 0 )
		{
			if( KonfiguracjaUst[i].opcje != 0 )
			{
				for (ii=0; ii<UstawieniaWarIlosc; ii++)
				{
					if( KonfiguracjaUst[i].opcje == KonfiguracjaWar[ii].opcje )
					{
						if( PobierzOpcje(KonfiguracjaUst[i].nazwaopcji) == atoi(KonfiguracjaWar[ii].wartosc) )
						{
						   sprintf(tmp_buff, "%s=%s\r\n", KonfiguracjaUst[i].nazwa, KonfiguracjaWar[ii].nazwa);
						   
						   ok=1;
						   break;
						}
					}
				}
			}
			else
			{
				sprintf(tmp_buff, "%s=%i\r\n", KonfiguracjaUst[i].nazwa, PobierzOpcje(KonfiguracjaUst[i].nazwaopcji));
				
				ok=1;
			}
		}
		else
		{
			for (ii=0; ii<UstawieniaWarIlosc; ii++)
			{
				if( KonfiguracjaUst[i].opcje == KonfiguracjaWar[ii].opcje )
				{
					////if (StatusOpcji(KonfiguracjaUst[i].byte, KonfiguracjaUst[i].bit, KonfiguracjaWar[ii].wartosc)==1)
					if (PobierzKodKreskowy(KonfiguracjaUst[i].byte, KonfiguracjaUst[i].bit, KonfiguracjaWar[ii].wartosc) == TRUE)
					{
						sprintf(tmp_buff, "%s=%s\r\n" ,KonfiguracjaUst[i].nazwa, KonfiguracjaWar[ii].nazwa);
						ok=1;
						break;
					}
				}
			}
		}

		if( ok == 1 )
		{
			DataLen = strlen(tmp_buff);

			tmp_buff[DataLen] = 0;
			if(write(FileName, tmp_buff, DataLen) != DataLen)
			{
				return FALSE;
			}
		}
		else
		{
			return FALSE;
		}      
	}

	return TRUE;
}
int CreateInfo(int FileName)
{
	int i=0;
	char tmp_buff[512];
	int DataLen=0;

	sprintf(tmp_buff,"#INFO\r\n");
	DataLen = strlen(tmp_buff);
	tmp_buff[DataLen] = 0;

	if(write(FileName, tmp_buff, DataLen) != DataLen)
	{
		return FALSE;
	}

	for(i=0; i<11; i++)
	{
		switch(i)
		{

			case 0: sprintf(tmp_buff,"%s=%s\r\n","M/D",ManufactureDate()); break;
			case 1: sprintf(tmp_buff,"%s=%s\r\n","H/W",HardwareVersion()); break;
			case 2: sprintf(tmp_buff,"%s=%s-%01d\r\n","DEV",DeviceType(), KeypadLayout()); break;
			case 3: sprintf(tmp_buff,"%s=%s\r\n","OS",KernelVersion()); break;
		#if MACHINETYPE == 8300
			case 4: sprintf(tmp_buff,"%s=%3d\r\n","RAM",BaseRamSize()+RamSize()); break;
		#else
			case 4: sprintf(tmp_buff,"%s=%3d\r\n","RAM",RamSize()); break;
		#endif
			case 5: sprintf(tmp_buff,"%s=%3d\r\n","FLASH",FlashSize()); break;
			case 6: sprintf(tmp_buff, "%s=%s\r\n", "S/N", SerialNumber()); break;
			case 7: sprintf(tmp_buff,"%s=%s\r\n","PROGRAM",Wersja); break;
			case 8:
				switch(Konfiguracja.Licencja)
				{
					case LIC_PELNA:
						sprintf(tmp_buff,"TYP_LICENCJI=Pe³na\r\n"); break;
					case LIC_PELNA_K:
						sprintf(tmp_buff,"TYP_LICENCJI=Pe³na + Kompletacja\r\n"); break;
					case LIC_PODSTAWOWA:
						sprintf(tmp_buff,"TYP_LICENCJI=Podstawowa\r\n"); break;
					case LIC_PODSTAWOWA_K:
						sprintf(tmp_buff,"TYP_LICENCJI=Podstawowa + Kompletacja\r\n"); break;
					case LIC_KOMPLETACJA:
						sprintf(tmp_buff,"TYP_LICENCJI=Kompletacja\r\n"); break;
					default:
						sprintf(tmp_buff,"TYP_LICENCJI=Demonstarcyjna\r\n"); break;
				}
				break;
			case 9: 

				if(Konfiguracja.Licencja == LIC_DEMO || Konfiguracja.Licencja == LIC_KOMPLETACJA)
				{
					sprintf(tmp_buff, "%s=%s\r\n", "KOD_LICENCJI_INWENTARYZATOR", "Brak");
				}
				else
				{
					sprintf(tmp_buff, "%s=%s\r\n", "KOD_LICENCJI_INWENTARYZATOR", Konfiguracja.LicencjaKluczInw);
				}
				break;
			case 10:
				if(Konfiguracja.Licencja == LIC_DEMO || Konfiguracja.Licencja == LIC_PODSTAWOWA || Konfiguracja.Licencja == LIC_PELNA)
				{
					sprintf(tmp_buff, "%s=%s\r\n", "KOD_LICENCJI_KOMPLETACJA", "Brak");
				}
				else
				{
					sprintf(tmp_buff, "%s=%s\r\n", "KOD_LICENCJI_KOMPLETACJA", Konfiguracja.LicencjaKluczKom);
				}
				break;
		}

		DataLen = strlen(tmp_buff); tmp_buff[DataLen] = 0;

		if(write(FileName, tmp_buff, DataLen) != DataLen)
		{
			return FALSE;
		}
	}

	return TRUE;
}
//==================================================================================
int Wyslij (int plik, char* DokumentId)
{
	clr_scr();

	#if MACHINETYPE == 8000
		Naglowek((char *)"Wysy³anie danych   ");
	#else
		Naglowek((char *)"Wysy³anie danych                  ");
	#endif

	if(BazaLiczbaRekordow(plik)>0)
	{
		if(Konfiguracja.TransmisjaProtokol == CIPHERLAB)
		{
			return CipherlabWyslij(plik, DokumentId);
		}

		if(Konfiguracja.TransmisjaProtokol == NOVITUS)
		{
			return NovitusWyslij(plik, DokumentId);
		}
	}
	else
	{
		Komunikat(19, 0);
	}

	return FALSE;
}
int WyslijKonfig (BYTE WyslacPliki)
{
	clr_scr();

	#if MACHINETYPE == 8000
		Naglowek((char *)"Wysy³anie danych   ");
	#else
		Naglowek((char *)"Wysy³anie danych                  ");
	#endif

	//if(Konfiguracja.TransmisjaProtokol == CIPHERLAB)
	//{
	////	return CipherlabWyslijKonfig();
	//}

	//if(Konfiguracja.TransmisjaProtokol == NOVITUS)
	//{
		return NovitusWyslijKonfig(WyslacPliki);
	//}

	//return FALSE;
}
int Odbierz (int plik)
{
	clr_scr();

	#if MACHINETYPE == 8000
		Naglowek((char *)"Odbieranie danych  ");
	#else
		Naglowek((char *)"Odbieranie danych                 ");
	#endif

	if(Konfiguracja.TransmisjaProtokol == CIPHERLAB)
	{
		return CipherlabOdbierz(plik);
	}
	
	if(Konfiguracja.TransmisjaProtokol == NOVITUS)
	{
		return NovitusOdbierz(plik);
	}

	return FALSE;
}
int OdbierzKonfig (void)
{
	clr_scr();

	#if MACHINETYPE == 8000
		Naglowek((char *)"Odbieranie danych  ");
	#else
		Naglowek((char *)"Odbieranie danych                 ");
	#endif

	//if(Konfiguracja.TransmisjaProtokol == CIPHERLAB)
	//{
	//	//return CipherlabOdbierzKonfig();
	//}

	//if(Konfiguracja.TransmisjaProtokol == NOVITUS)
	//{
		return NovitusOdbierzKonfig();
	//}

	//return FALSE;
}
//==================================================================================
void StworzIndeks(int plik)
{
	int p, i;
	char NazwaPliku[512];
	char tNazwaPliku[512];
	switch(plik)
	{
		case TOWARY: strcpy(NazwaPliku, TOWARY_N); break;
		case DOKUMENTY: strcpy(NazwaPliku, DOKUMENTY_N); break;
		case KONTRAHENCI: strcpy(NazwaPliku, KONTRAHENCI_N); break;
		case RODZAJEDOK: strcpy(NazwaPliku, RODZAJEDOK_N); break;
		case KOMPLETACJA: strcpy(NazwaPliku, KOMPLETACJA_N); break;
	}
	#if MACHINETYPE == 8400 || MACHINETYPE == 8200 || MACHINETYPE == 8600
		//if((ffreebyte()!= -1L) && (nZapisKarta==1))
		//{
		//	sprintf(tNazwaPliku, "A:\\%s", NazwaPliku);
		//}
		//else
		{
			strcpy(tNazwaPliku, NazwaPliku);
		}
	#else
		strcpy(tNazwaPliku, NazwaPliku);
	#endif

	remove_index(uPlik[plik], 1);

	close_DBF (uPlik[plik]);

	remove (tNazwaPliku);

	PlikKonfiguracja(plik);

	uPlik [plik] = create_DBF (tNazwaPliku, Plik [plik].Dlugosc);

	i = Plik [plik].Indeks;
	create_index (uPlik [plik], 1, Plik [plik].Rekord [i], Plik [plik].DlugoscRekordu [i]);

	// przypisanie uchwytów plików do zmiennych
	switch(plik)
	{
		case TOWARY: PlikTowary = uPlik[TOWARY]; break;
		case DOKUMENTY: PlikDokumenty = uPlik[DOKUMENTY]; break;
		case KONTRAHENCI: PlikKontrahenci = uPlik[KONTRAHENCI]; break;
		case RODZAJEDOK: PlikRodzajeDokumentow = uPlik[RODZAJEDOK]; break;
		case KOMPLETACJA: PlikKompletacja = uPlik[KOMPLETACJA]; break;
	}
}
int NovitusWyslij( int plik, char* DokumentId )
{
	int CommId, DataLen, i, err_desc;
	char Buff[1024], key;
	long k , n;
	int exPlik;
	int j;
	int lookup;
	int oDemoLicz = 0;
	char DokumentIdTmp[256];

	clr_scr();
	Naglowek((char *)"     Wysy³anie      ");

	switch(plik)
	{
		case DOKUMENTY: exPlik = PlikDokumenty; break;
		case TOWARY: exPlik = PlikTowary; break;
		case KONTRAHENCI: exPlik = PlikKontrahenci; break;
		case RODZAJEDOK: exPlik = PlikRodzajeDokumentow; break;
		case KOMPLETACJA: exPlik = PlikKompletacja; break;
	}
	n = BazaLiczbaRekordow(plik);

	lseek_DBF (exPlik, 1, 0, 1);
	get_member (exPlik, 1, (char *)Dane);

	if (plik == DOKUMENTY) KonwersjaExportRekordu();
	if (plik == TOWARY) KonwersjaExportTowar();
	if (plik == KONTRAHENCI) KonwersjaExportKontrahent();
	if (plik == RODZAJEDOK) KonwersjaExportRodzajDok();

	lookup = strlen((char*)Dane) + 2;
	if( (strlen(DokumentId) > 0) && ((plik == DOKUMENTY) || (plik == KOMPLETACJA)) )
	{
		// liczenie liczby rekordów w dokumencie
		n = 0;
		while (1)
		{
			get_member(exPlik,1,(char *)Dane);
			if(plik==DOKUMENTY)
			{
				Dane[Konfiguracja.BazaLokDokumentKod]=0;
			}
			else
			{
				Dane[Konfiguracja.BazaKompletacjaNumerDokumentu]=0;
			}

			if (strcmp((char*)Dane,DokumentId)==0) n++;

			lseek_DBF (exPlik, 1, 1, 0); // do przodu
			if (read_error_code()==15) break;
		}
	}
	NoBytes = n * lookup;

	CommId = TF_OpenPort();

	if(CommId < 0) return 0;

	if(CommId > 0)
	{
		TF_InitProt(CommId);

		Progress = wait = 0;
		#if (MACHINETYPE == 8000 || MACHINETYPE == 8300)
			if (Konfiguracja.TransmisjaPort == COMM_IrDA)
				sprintf(Buff, "%ldI0", NoBytes);	
			else
				sprintf(Buff, "%ldA0", NoBytes);	
		#else
			sprintf(Buff, "%ldA0", NoBytes);	
		#endif

		if((err_desc = TF_WritePkt(Buff, strlen(Buff))) < 1)
		{
			TF_ClosePort(CommId);
			DiagnosticMsg(err_desc, CommId);
			return err_desc;
		}

		lseek_DBF(exPlik, 1, 0, 1);

		k = 0;

		while (k < n)
		{
			key = getchar();
			if (Klawisz(key, (char*)"ESC"))
			{
				TF_CutTrans();
				key = ' ';
				TF_ClosePort(CommId);
				return -1;
			}

			get_member(exPlik,1,(char *)Dane);
			strcpy(DokumentIdTmp, (char*)Dane);
			if(plik==DOKUMENTY)
			{
				DokumentIdTmp[Konfiguracja.BazaLokDokumentKod] = 0;
			}
			else
			{
				DokumentIdTmp[Konfiguracja.BazaKompletacjaNumerDokumentu] = 0;
			}

			if ((strlen(DokumentId)>0)&&((plik==DOKUMENTY)||(plik==KOMPLETACJA)))
			{
				if (strcmp(DokumentIdTmp,DokumentId) != 0)
				{
					lseek_DBF (exPlik, 1, 1, 0);
					if (read_error_code()==15) break;
					continue;
				}
			}

			if (plik == DOKUMENTY) KonwersjaExportRekordu();
			if (plik == TOWARY) KonwersjaExportTowar();
			if (plik == KONTRAHENCI) KonwersjaExportKontrahent();
			if (plik == RODZAJEDOK) KonwersjaExportRodzajDok();

			if( (Konfiguracja.Licencja==LIC_DEMO) && (oDemoLicz >= Konfiguracja.Demo) )
			{
				int i=0;
				for(i=0; i<strlen((char*)Dane); i++)
					Dane[i]=' ';
			}



			lseek_DBF (exPlik, 1, 1, 0);

			if (Dane[0]==0) continue;

			j = strlen((char*)Dane);

			if (j < 2)
			{// invalid data
				n--;
				continue;
			}

			strcat((char*)Dane,"\r\n");

			if((err_desc = TF_WritePkt((char *)Dane, lookup)) < 1)
			{
				TF_ClosePort(CommId);
				DiagnosticMsg(err_desc, CommId);
				return err_desc;
			}

			Progress += lookup;
			oDemoLicz++;
			k++;
		}
	Koniec:
		Komunikat(10,0);

		if( (Konfiguracja.Licencja==LIC_DEMO) && (oDemoLicz >= Konfiguracja.Demo) )
		{
			Komunikat(17,0);
		}

		TF_ClosePort(CommId);
		return 1;
	}
	else
	{
		Komunikat(52,0);
		return -1;
	}
}
int NovitusOdbierz(int plik)
{
	int CommId=0;
	unsigned long int DataLenFile=0, DataLenTmpFile=0;
	unsigned int DataLenTmp=0;
	int procenty=0, prodenty_n=0, fr=0;
	char tmp_buff[1024];
	char Proc_naz[8];
	long int m = 0;
	int exPlik=0, tmp_file_handle=0;
	int PortType=0, PortSettings=0;
	int oDemoLicz = 0;

	clr_scr();
	Naglowek((char *)"     Odbieranie     ");

	CommId = TF_OpenPort();

	if(CommId>0)
	{
		StworzIndeks(plik);

		switch(plik)
		{
			case DOKUMENTY: exPlik = PlikDokumenty; break;
			case TOWARY: exPlik = PlikTowary; break;
			case KONTRAHENCI: exPlik = PlikKontrahenci; break;
			case RODZAJEDOK: exPlik = PlikRodzajeDokumentow; break;
			case KOMPLETACJA: exPlik = PlikKompletacja; break;
		}

		if (access(TMP_FILE_NAME)) remove(TMP_FILE_NAME);

		odbieramy_DB_IDX=1;
		procenty = 0;
		tmp_file_handle = open(TMP_FILE_NAME);	

		fr = FileReceiveN(CommId, tmp_file_handle);

		if ( fr == 1 )	//prawid³owo odebrano plik DAT, rób konwersjê na DB+IDX
		{
			TF_RewindFile(tmp_file_handle);

			Czysc();
			Naglowek((char *)"     Odbieranie     ");

			#if MACHINETYPE == 8000
				TF_PrintAt(0, 2, (char *)"Trwa konwersja ");
			#else
				TF_PrintAt(0, 2, (char *)"Trwa konwersja... ");
			#endif

			Progress = 0;
			m = -1;
			DataLenFile = filelength(tmp_file_handle);

			while(DataLenTmp = TF_ReadFileLine(tmp_file_handle, tmp_buff))
			{
				if (Klawisz(getchar(), (char*)"ESC"))
				{
					DiagnosticMsg(-1, CommId);
					return -1;
				}

				TF_ShowSandGlass();
				_KeepAlive__();
				
				#if MACHINETYPE != 8600
					TF_RewindFile(tmp_file_handle);
					read(tmp_file_handle, tmp_buff, DataLenTmp+2);
					delete_top(tmp_file_handle, DataLenTmp+2);
				#endif

				tmp_buff[DataLenTmp] = 0;
				strcpy((char *)Dane,(char *)tmp_buff);

				KonwersjaImport(plik);

				if (free_memory() <= (long)strlen((char*)Dane)) 
				{
					DiagnosticMsg(-10, CommId);
					return -10;
				}

				oDemoLicz++;
				if( (Konfiguracja.Licencja==LIC_DEMO) && (oDemoLicz > Konfiguracja.Demo) ) // ograniczenie wersji demo
				{
					break;
				}
				else
				{
					if(BazaZmienRekord(plik, DODAJ, 1, (char*)Dane, NIE)==FALSE)
					{
						DiagnosticMsg(-11,CommId);
						return -11;
					}
				}

				#if MACHINETYPE == 8600
					DataLenTmpFile = tell(tmp_file_handle);
					prodenty_n = (100 - (DataLenFile - DataLenTmpFile)*100/DataLenFile);
				#else
					DataLenTmpFile = filelength(tmp_file_handle);
					prodenty_n = (DataLenFile - DataLenTmpFile)*100/DataLenFile;
				#endif

				if (procenty != prodenty_n)
				{
					procenty = prodenty_n;
					sprintf(Proc_naz, "%i", procenty);
					TF_PrintAt(0, 3, (char *)"Wykonano: ");
					TF_PrintAt(10, 3, (char *)Proc_naz);
					TF_PrintAt(13, 3, (char *)"%");
				}

				strcpy((char*)Dane, " ");

			}//while
Koniec:
			close(tmp_file_handle);

			Czysc();

			Komunikat(10,0);

			if( (Konfiguracja.Licencja==LIC_DEMO) && (oDemoLicz > Konfiguracja.Demo) )
			{
				Komunikat(17,0);
			}

			TF_ClosePort(CommId);
			return 1;
		}
		else
		{
			if (access(TMP_FILE_NAME)) remove(TMP_FILE_NAME);
			DiagnosticMsg(fr, CommId);
			return fr;
		}
	}
	else
	{
		Komunikat(52,0);
		return -1;
	}
}
int CipherlabWyslij(int plik, char* DokumentId)
{
	BYTE  Header[2], c=0;
	int   i=0, j=0, nSum=0;
	long  k=0, n=0;
	int   oDemoLicz = 1;
	int CommId;
	int exPlik;
	char DokumentIdTmp[256];

	switch(plik)
	{
		case DOKUMENTY: exPlik = PlikDokumenty; break;
		case TOWARY: exPlik = PlikTowary; break;
		case KONTRAHENCI: exPlik = PlikKontrahenci; break;
		case RODZAJEDOK: exPlik = PlikRodzajeDokumentow; break;
		case KOMPLETACJA: exPlik = PlikKompletacja; break;
	}

	#ifdef CPT8000
			TF_PrintAt(0, 2, (char *)"Oczekiwanie na");
			TF_PrintAt(0, 3, (char *)"po³¹czenie...");
	#else	  
			TF_PrintAt(0, 2, (char *)"Oczekiwanie na      ");
			TF_PrintAt(0, 3, (char *)"po³¹czenie...");
	#endif

	CommId = TF_OpenPort();

	if(CommId < 0) return 0;

	if(CommId>0)
	{
		if (!ReceiveCom ((BYTE *) READ, UPLOADMODE))
			return FALSE;

		#ifdef CPT8000
			TF_PrintAt(0, 2, (char *)"Trwa transmisja ");
		#else	  
			TF_PrintAt(0, 2, (char *)"Trwa transmisja...  ");
		#endif
		TF_PrintAt(0, 3, (char *)"Przes³ano:      ");

		BazaUstawPoczatek(plik, 1);

		if( (strlen(DokumentId) > 0) && ((plik == DOKUMENTY) || (plik == KOMPLETACJA)) )
		{
			// liczenie liczby rekordów w dokumencie
			n = 0;
			while (1)
			{
				get_member(exPlik, 1, (char *)Dane);
				if(plik == DOKUMENTY)
				{
					Dane[Konfiguracja.BazaLokDokumentKod] = 0;
				}
				else
				{
					Dane[Konfiguracja.BazaKompletacjaNumerDokumentu] = 0;
				}

				if (strcmp((char*)Dane, DokumentId) == 0) n++;

				BazaPrzesunDoDolu(plik, 1);
				if (read_error_code()==15) break;
			}//while
		}
		else
		{
			n = BazaLiczbaRekordow(plik);
		}
		BazaUstawPoczatek(plik, 1);

		k = 0;
		while (k < n)
		{
			TF_ShowSandGlass();

			get_member(exPlik,1,(char *)Dane);
			strcpy(DokumentIdTmp, (char*)Dane);
			if(plik==DOKUMENTY)
			{
				DokumentIdTmp[Konfiguracja.BazaLokDokumentKod] = 0;
			}
			else
			{
				DokumentIdTmp[Konfiguracja.BazaKompletacjaNumerDokumentu] = 0;
			}

			if ((strlen(DokumentId)>0)&&((plik==DOKUMENTY)||(plik==KOMPLETACJA)))
			{
				if (strcmp(DokumentIdTmp,DokumentId) != 0)
				{
					lseek_DBF (exPlik, 1, 1, 0);
					if (read_error_code()==15) break;
					continue;
				}
			}

			// format dokum. wyjs
			if (plik == DOKUMENTY) KonwersjaExportRekordu();
			if (plik == TOWARY) KonwersjaExportTowar();
			if (plik == KONTRAHENCI) KonwersjaExportKontrahent();
			if (plik == RODZAJEDOK) KonwersjaExportRodzajDok();

			if( (Konfiguracja.Licencja == LIC_DEMO) && (oDemoLicz >= Konfiguracja.Demo) )
			{
				int i;
				for(i=0; i<strlen((char*)Dane); i++)
				{
					Dane[i]=' ';
				}
			}
			oDemoLicz++;

			BazaPrzesunDoDolu(plik, 1);

			j = strlen((char*)Dane);
			if (j < 2)
			{// invalid data
				n--;
				continue;
			}

			nSum = Header [0] = k++ % 10;            // rotate from 0 to 9

			for (i=0; i<j; i++)
				nSum += Dane [i];
			Dane [j] = nSum & 0xFF;
			if (Dane [j] == '\r')                  // make sure the checksum isn't '\r'
				Dane [j]++;
			j++;
			Dane [j] = nSum >> 8;
			if (Dane [j] == '\r')                  // make sure the checksum isn't '\r'
				Dane [j]++;
			Dane [++j] = '\r';
			Dane [++j] = '\0';

			ltoa (k, (char*)Bufor, 10);
			TF_PrintAt(10, 3, (char *)Bufor);			// show data transfered

			while (TRUE)
			{
				nwrite_com (TF_PortType, (char *)Header, 1);

				while (!com_eot (TF_PortType));

				nwrite_com (TF_PortType, (char *)Dane, j);

				while (!com_eot (TF_PortType));

				while (TRUE)
				{
					i = 0;
					while (i < 10)
					{
						if (Klawisz(getchar(), (char*)"ESC"))
						{
							Podswietlenie();

							Komunikat(11,0);
							WriteCOM ((char *) OVERU, UPLOADMODE);
							write_com (TF_PortType, (char *) OVERU);
							TF_ClosePort(CommId);
							clr_scr ();
							return FALSE;
						}

						if (read_com (TF_PortType, (char *)&c))
							Bufor [i++] = c;

						if (c == '\r')
							break;
					}
					Bufor [i] = '\0';

					if ((!strcmp ((char*)Bufor, NAK)) || (!strcmp ((char*)Bufor, ACK)))
						break;
				}

				if (!strcmp ((char*)Bufor, NAK))
					continue;
				break;
			}
		}

Koniec:

		WriteCOM ((char *) OVERU, UPLOADMODE);
		write_com (TF_PortType, (char *) OVERU);

		TF_ClosePort(CommId);

		Komunikat(10,0);

		if( (Konfiguracja.Licencja == LIC_DEMO) && (oDemoLicz >= Konfiguracja.Demo) )
		{
			Komunikat(17,0);
		}

		return TRUE;
	}
	else
	{
		Komunikat(52,0);
		return -1;
	}
}
int CipherlabOdbierz(int plik)
{
	BYTE   bMemoryFull=FALSE, cFlag=0;
	BYTE   c=0, d=0;
	int    i=0, j=0, k=0, nSum=0;
	long   m=0;
	long int    oDemoLicz = 0;
	BYTE   oDemoErr = FALSE;
	int exPlik, CommId;

	#ifdef CPT8000
			TF_PrintAt(0, 2, (char *)"Oczekiwanie na");
			TF_PrintAt(0, 3, (char *)"po³¹czenie...");
	#else	  
			TF_PrintAt(0, 2, (char *)"Oczekiwanie na      ");
			TF_PrintAt(0, 3, (char *)"po³¹czenie...");
	#endif

	CommId = TF_OpenPort();

	if(CommId < 0) return 0;

	if(CommId > 0)
	{
		if (!ReceiveCom ((BYTE *) CIPHER, DOWNLOADMODE))
			return FALSE;

		#ifdef CPT8000
			TF_PrintAt(0, 2, (char *)"Trwa transmisja ");
		#else	  
			TF_PrintAt(0, 2, (char *)"Trwa transmisja...  ");
		#endif
		TF_PrintAt(0, 3, (char *)"Przes³ano:      ");

		StworzIndeks(plik);

		switch(plik)
		{
			case DOKUMENTY: exPlik = PlikDokumenty; break;
			case TOWARY: exPlik = PlikTowary; break;
			case KONTRAHENCI: exPlik = PlikKontrahenci; break;
			case RODZAJEDOK: exPlik = PlikRodzajeDokumentow; break;
			case KOMPLETACJA: exPlik = PlikKompletacja; break;
		}

		m = 0;
		while (TRUE)
		{
			TF_ShowSandGlass();

			i = 0;
			c = '\0';
			while (TRUE)
			{
				if (Klawisz(getchar(), (char*)"ESC"))
				{
					Podswietlenie();
					Komunikat(11, 0);
					TF_ClosePort(CommId);
					return FALSE;
				}

				if (read_com (TF_PortType, (char *)&c))
					*(Dane + i++) = c;

				if (c == '\r')
					break;
			}

			if (i < 2)
				continue;

			i--;
			*(Dane+i) = '\0';

			if (!strcmp ((char *)Dane, OVER))
			{
				WriteCOM ((char *) ACK, DOWNLOADMODE);
				break;
			}

			nSum = 0;
			for (j=0; j<i-2; j++)
				nSum += Dane [j];
			j = nSum / 256;
			if (j == 13)
				j++;
			k = nSum % 256;
			if (k == 13)
				k++;

			if ((j != Dane [i-2]) || (k != Dane [i-1]))    // if wrong checksum
			{
				WriteCOM ((char *) NAK, DOWNLOADMODE);
				continue;
			}

			Dane [i-2] = '\0';

			if (free_memory () <= (long)i)                     // check memory
			{
				WriteCOM ((char *) FULL, DOWNLOADMODE);
				bMemoryFull = TRUE;
				break;
			}

			KonwersjaImport(plik);

			if( (Konfiguracja.Licencja == LIC_DEMO) && (oDemoLicz >= Konfiguracja.Demo) )
			{
				WriteCOM ((char *) NAK, DOWNLOADMODE);
				break;
			}
			oDemoLicz++;

			if (d = Plik [plik].Separator)        // lookup field properties : use delimiter
			{
				int  n, nLmt=(i-2);

				j = k = n = 0;

				for (i=0; i<strlen ((char *)Dane); i++)
				{
					c = Dane [i];
					if (c == d)                                // delimiter
					{
						while (n++ < Plik [plik].DlugoscRekordu [j])
							Bufor [k++] = ' ';				   // add space

						if (++j < Plik [plik].PolaIlosc)
							Bufor [k++] = d;				    // add delimiter

						n = 0;
					}
					else
					{
						if (n < Plik [plik].DlugoscRekordu [j])
						{
							Bufor [k++] = c;					// add data
							n++;
						}
					}
				}

				while (n < Plik [plik].DlugoscRekordu [j])
				{
					Bufor [k++] = ' ';						    // add space
					n++;
				}

				Bufor [k] = '\0';
				strcpy ((char *)Dane, (char *)Bufor);
			}

			if (*Dane)
			{
				if(!oDemoErr) add_member (exPlik, (char *)Dane);   // add to database
			}
			else
			{
				WriteCOM ((char *) NAK, DOWNLOADMODE);
				continue;
			}

			ltoa (++m, (char*)Bufor, 10);
			TF_PrintAt(10, 3, (char *)Bufor);  // show data transfered
			WriteCOM ((char *) ACK, DOWNLOADMODE);
		}

Koniec:
		TF_ClosePort(CommId); 

		if( (Konfiguracja.Licencja==LIC_DEMO) && (oDemoLicz >= Konfiguracja.Demo) )
		{
			Komunikat(17, 0);
		}

		if (bMemoryFull)
		{
			Komunikat(14, 0);
		}
		else
		{
			Komunikat(10, 0);
		}

		return TRUE;
	}
	else
	{
		Komunikat(52,0);
		return -1;
	}
}
// KONFIGURACJA KOLEKTORA ==========================================================
int NovitusWyslijKonfig(BYTE WyslacPliki)
{
	int CommId, DataLen, i, err_desc, lookup, h;
	char Buff[1024];
	long k , n;
	int oDemoLicz[LICZBA_PLIKOW];
	char DokumentIdTmp[256];
	int tmp_file_handle = 0;
	unsigned long int DataLenFile=0, DataLenTmpFile=0;
	unsigned int DataLenTmp=0;
	int intDataLenTmp = 0;
	char tmp_buff[1024];

	tmp_file_handle = CreateFile(TMP_FILE_NAME);

	if(CreateInfo(tmp_file_handle) == TRUE &&  CreateKonfig(tmp_file_handle) == TRUE)
	{
		NoBytes = 0;

		if(WyslacPliki)
		{
			for(h = 0; h<LICZBA_PLIKOW; h++)
			{
				if (Konfiguracja.Licencja == LIC_DEMO)
				{
					if(h > 0) break;
				}

				if (h == DOKUMENTY) continue;

				n = BazaLiczbaRekordow(h);

				if (n > 0)
				{
					BazaUstawPoczatek(uPlik[h], 1);

					get_member (uPlik[h], 1, (char *)Dane);

					lookup = strlen((char*)Dane)+2;
					NoBytes += n * lookup;

					switch(h)
					{
						case DOKUMENTY: NoBytes += 10+2; break;
						case TOWARY: NoBytes += 7+2; break;
						case KONTRAHENCI: NoBytes += 12+2; break;
						case RODZAJEDOK: NoBytes += 12+2; break;
						case KOMPLETACJA: NoBytes += 12+2; break;
					}
				}
			}
		}

		TF_RewindFile(tmp_file_handle);
		NoBytes += filelength(tmp_file_handle);

		CommId = TF_OpenPort();

		if(CommId>0)
		{
			TF_InitProt(CommId);

			Progress = wait = 0;
			#if (MACHINETYPE == 8000 || MACHINETYPE == 8300)
				if (Konfiguracja.TransmisjaPort == COMM_IrDA)
					sprintf(Buff, "%ldI0", NoBytes);	
				else
					sprintf(Buff, "%ldA0", NoBytes);	
			#else
				sprintf(Buff, "%ldA0", NoBytes);	
			#endif

			if((err_desc = TF_WritePkt(Buff, strlen(Buff))) < 1)
			{
				TF_ClosePort(CommId);
				DiagnosticMsg(err_desc, CommId);
				RemoveFile(tmp_file_handle, TMP_FILE_NAME);
				return err_desc;
			}

			DataLenFile = filelength(tmp_file_handle);

			TF_RewindFile(tmp_file_handle);

			while((DataLenTmp = TF_ReadFileLine(tmp_file_handle, tmp_buff)) > 0)
			{
				if (Klawisz(getchar(), (char*)"ESC"))
				{
					TF_CutTrans();
					RemoveFile(tmp_file_handle, TMP_FILE_NAME);
					return -1;
				}

				_KeepAlive__();
				//todo: dodaæ usuwanie danych po ich odczytaniu

				if (tmp_buff[0]==0) continue;

				if (DataLenTmp < 2)	// invalid data
				{
						n--;
						continue;
				}

				strcat(tmp_buff,"\r\n");

				if((err_desc = TF_WritePkt((char *)tmp_buff, (DataLenTmp + 2))) < 1)
				{
					DiagnosticMsg(err_desc, CommId);
					RemoveFile(tmp_file_handle, TMP_FILE_NAME);
					return err_desc;
				}

				Progress += DataLenTmp+2;
				k++;
			}

			if(WyslacPliki)
			{
				for(h = 0; h < LICZBA_PLIKOW; h++)
				{
					if (h == DOKUMENTY) continue;

					n = BazaLiczbaRekordow(h);

					if (n > 0)
					{
						switch(h)
						{
							case DOKUMENTY: strcpy((char *)Dane,(char *)"#DOKUMENTY"); break;
							case TOWARY: strcpy((char *)Dane,(char *)"#TOWARY"); break;
							case KONTRAHENCI: strcpy((char *)Dane,(char *)"#KONTRAHENCI"); break;
							case RODZAJEDOK: strcpy((char *)Dane,(char *)"#RODZAJE_DOK"); break;
							case KOMPLETACJA: strcpy((char *)Dane,(char *)"#KOMPLETACJA"); break;
						}

						lookup = strlen((char*)Dane) + 2;

						strcat((char*)Dane,"\r\n");
						if((err_desc = TF_WritePkt((char *)Dane, lookup)) < 1)
						{
							  DiagnosticMsg(err_desc, CommId);
							  return err_desc;
						}
						Progress += lookup;

						get_member (uPlik[h], 1, (char *)Dane);

						lookup = strlen((char*)Dane)+2;

					//BazaUstawPoczatek(h, 1);
					lseek_DBF (uPlik[h], 1, 0, 1);

						k = 0;

						while (k < n)
						{
							if (getchar() == KEY_ESC)
							{
								TF_CutTrans();
								TF_ClosePort(CommId);
								return -1;
							}

							get_member(uPlik[h], 1, (char *)Dane);

							if( (Konfiguracja.Licencja == LIC_DEMO) && (oDemoLicz[h] >= Konfiguracja.Demo) )
							{
								int i=0;
								for(i=0; i<strlen((char*)Dane); i++)
									Dane[i]=' ';
							}

						//BazaPrzesunDoDolu(h, 1);
						lseek_DBF (uPlik[h], 1, 1, 0);

							if (Dane[0]==0) continue;

						if (strlen((char*)Dane) < 2)	// invalid data
						{
								n--;
								continue;
							}

							strcat((char*)Dane,"\r\n");

							if((err_desc = TF_WritePkt((char *)Dane, lookup)) < 1)
							{
								TF_ClosePort(CommId);
								DiagnosticMsg(err_desc, CommId);
								return err_desc;
							}

							Progress += lookup;
							//oDemoLicz[h]++;
							k++;
						}
					}
				}
			}

			TF_EndOfTx();
			TF_ClosePort(CommId);

			//Komunikat(10,0);

			//TODO: poprawiæ warunki wyœwietlanie komunikatu o ograniczeniach wersji demo
			//if( (Konfiguracja.Licencja == LIC_DEMO) && (oDemoLicz >= Konfiguracja.Demo) )
			//{
			//	Komunikat(17,0);
			//}

			RemoveFile(tmp_file_handle, TMP_FILE_NAME);

			return TRUE;
		}
		else
		{
			RemoveFile(tmp_file_handle, TMP_FILE_NAME);

			Komunikat(52,0);
			return -1;
		}
	}
	else
	{
		RemoveFile(tmp_file_handle, TMP_FILE_NAME);

		Komunikat(14,0); // je¿eli nie mo¿na utworzyæ plików to prawdopodobnie jest za ma³o pamiêci lub pamiêæ jest uszkodzona
		return -2;
	}
}
int NovitusOdbierzKonfig(void)	//odbieranie konfiguracji
{
	int CommId;
	int fr=0;
	int FileHandle=0, tmp_file_handle=0;

	if (access(TMP_FILE_NAME)) remove(TMP_FILE_NAME);

	tmp_file_handle = open(TMP_FILE_NAME);	

	CommId = TF_OpenPort();

	fr = FileReceiveN(CommId, tmp_file_handle);

	if ( fr == 1 )	//prawid³owo odebrano plik DAT, rób konwersjê na DB+IDX
	{
		int w = OdbiorKonfiguracji(tmp_file_handle);

		if(w < 0)
		{
			DiagnosticMsg(w, CommId);
		}

		TF_ClosePort(CommId);

		return 1;
	}//if
	else
	{
		if (access(TMP_FILE_NAME)) remove(TMP_FILE_NAME);
		DiagnosticMsg(fr, CommId);
		return fr;
	}
}
//==================================================================================
int OdbiorKonfiguracji(int tmp_file_handle)
{
	unsigned long int DataLenFile, DataLenTmpFile;
	unsigned int DataLenTmp=0;
	int procenty=0, prodenty_n=0, fr=0;
	unsigned long int linia=0;
	char tmp_buff[512];
	char tmp_plik[512];
	char Proc_naz[8];
	int plikiImport=0;
	int ustawienia=0;
	unsigned char ustCzytnik[23];
	long int m = 0;
	int FileHandle=0;
	char key=0;
	BYTE c=0, d=0;
	int PortType=0, PortSettings=0, nL=0;
	int oDemoLicz = 0;
	int oDemoLiczT = 0;
	int oDemoLiczK = 0;
	int oDemoLiczR = 0;
	int oDemoLiczKm = 0;
	char DaneNaz[256];
	char DaneWar[256];
	int konfigError = FALSE;
	int i=0, ii=0, ok=FALSE;
	int j=0, k=0;
	int errKonfig=0;

	TF_RewindFile(tmp_file_handle);

	Czysc();

	#ifdef CPT8000
		TF_PrintAt(0, 2, (char *)"Trwa konwersja ");
	#else
		TF_PrintAt(0, 2, (char *)"Trwa konwersja... ");
	#endif

	Progress = 0;
	plikiImport=0;
	ustawienia=0;
	DataLenFile = filelength(tmp_file_handle);
	DataLenTmpFile = DataLenFile;

	//while( ((DataLenTmp = TF_ReadFileLine(tmp_file_handle, tmp_buff)) >= 0) && (DataLenTmpFile > 0) )
	while( DataLenTmp = TF_ReadFileLine(tmp_file_handle, tmp_buff) )
	{
		linia++;
		key = getchar();
		if (Klawisz(key, (char*)"ESC"))
		{
			key = ' ';
			return -1;
		}

		_KeepAlive__();
		//todo: dodaæ usuwanie danych po ich odczytaniu
		//TF_RewindFile(tmp_file_handle);
		//if(!read(tmp_file_handle, tmp_buff, DataLenTmp+2)) break;
		//delete_top(tmp_file_handle, DataLenTmp+2);					//usuñ odczytany rekord z pliku tymczasowego
		
		tmp_buff[DataLenTmp] = 0;
		strcpy((char *)Dane, (char *)tmp_buff);

		DataLenTmpFile = filelength(tmp_file_handle);

		prodenty_n = (DataLenFile - DataLenTmpFile)*100/DataLenFile;
		if (procenty != prodenty_n)
		{
			procenty = prodenty_n;
			sprintf(Proc_naz, "%i", procenty);
			TF_PrintAt(0, 3, (char *)"Wykonano: ");
			TF_PrintAt(10, 3, (char *)Proc_naz);
			TF_PrintAt(13, 3, (char *)"%");
		}
			
		if( strlen((char *)Dane) > 2 )
		{
			if (Dane[0] == 35) { //35 = #
				sprintf(tmp_plik, (char *)Dane);
				if( (strcmp("#USTAWIENIA", tmp_plik) == 0) || (strcmp("#INFO", tmp_plik) == 0) || (strcmp("#TOWARY", tmp_plik) == 0) || (strcmp("#KONTRAHENCI", tmp_plik) == 0) || (strcmp("#RODZAJE_DOK", tmp_plik) == 0) || (strcmp("#KOMPLETACJA", tmp_plik) == 0) )
				{
					if( strcmp("#USTAWIENIA", tmp_plik) == 0 )
						ustawienia++;
					else {
						if( (ustawienia > 0)  && (plikiImport == 0) ) {
							errKonfig = KonfiguracjaTest();

							if(errKonfig == KONFIGURACJA_OK) {
								ZapiszKonfiguracje();

								PlikUsun(TOWARY, 0);
								PlikUsun(KONTRAHENCI, 0);
								PlikUsun(RODZAJEDOK, 0);
								PlikUsun(KOMPLETACJA, 0);
							}
							else {
								WyswietlBladKonfiguracji(errKonfig);
								konfigError = TRUE;
							}
						}
						if (strcmp("#TOWARY",tmp_plik) == 0) {
							StworzIndeks((int)TOWARY);
						}
						if (strcmp("#KONTRAHENCI",tmp_plik) == 0) {
							StworzIndeks((int)KONTRAHENCI);
						}
						if (strcmp("#RODZAJE_DOK",tmp_plik) == 0) {
							StworzIndeks((int)RODZAJEDOK);
						}
						if (strcmp("#KOMPLETACJA",tmp_plik) == 0) {
							StworzIndeks((int)KOMPLETACJA);
						}
					}
					if( (strcmp("#TOWARY",tmp_plik) == 0) || (strcmp("#KONTRAHENCI",tmp_plik) == 0) || (strcmp("#RODZAJE_DOK",tmp_plik) == 0) || (strcmp("#KOMPLETACJA",tmp_plik) == 0) )
						plikiImport++;
				}
				else
					konfigError = TRUE;
			}
			else if (Dane[0] == 59) {
				//nie rób nic, puste pole w pliku konfiguracyjnym
			}
			else {
				if( (strcmp("#USTAWIENIA", tmp_plik) == 0) && (plikiImport == 0))
				{
					int i;
					int a=0,b=0,c=0;
					BYTE inDataWar;

					for (i=0; i<strlen((char *)Dane); i++)
					{
						if (Dane[i] == 61)
							a++;
						else
							if(a == 0)
								DaneNaz[b++] = Dane[i];
							else
								DaneWar[c++] = Dane[i];
					}
					DaneNaz[b] = 0;
					DaneWar[c] = 0;
					inDataWar = atoi((char *)DaneWar);

					for (i=0; i<UstawieniaIlosc; i++)
					{
						ok=0;
						if( strcmp("OPIS_KONTRAHENTA_W_MENU", DaneNaz)==0 ||
							strcmp("D£UGOŒÆ_OPIS_DOK/POZ", DaneNaz)==0 ||
							strcmp("DOK_WY_OPIS_DOK/POZ", DaneNaz)==0 ||
							strcmp("DOK_WE_OPIS_DOK/POZ", DaneNaz)==0 ||
							strcmp("DOK_WY_RODZAJ_DOKUMENTU", DaneNaz)==0 ||
							strcmp("KONTRAHENT_ID_KONTRAHENTA", DaneNaz)==0 ||
							strcmp("KONTRAHENT_OPIS_KONTRAHENTA", DaneNaz)==0 ||
							strcmp("DOK_WY_NR_DOKUMENTU", DaneNaz)==0 ||
							strcmp("OPIS_DO_DOKUMENTU", DaneNaz)==0 ||
							strcmp("OPIS_DO_POZYCJI", DaneNaz)==0 ||
							strcmp("KONTRAHENT_Z_POZA", DaneNaz)==0 ||
							strcmp("RODZAJ_DOKUMENTU_Z_POZA", DaneNaz)==0
							)
						{
							ok=3;
						}
						else {
							if( strcmp(KonfiguracjaUst[i].nazwa, (char*)DaneNaz)==0 ){
								if( KonfiguracjaUst[i].grupa == 0 ){
									if( KonfiguracjaUst[i].opcje != 0 ){
										for (ii=0; ii<UstawieniaWarIlosc; ii++)
										{
											if( KonfiguracjaUst[i].opcje == KonfiguracjaWar[ii].opcje ){
												if( strcmp((char*)DaneWar, KonfiguracjaWar[ii].nazwa)==0 ){
													inDataWar = atoi(KonfiguracjaWar[ii].wartosc);
													ok=1;
													break;
												}
											}
										}
									}
									else {
										ok=1;
									}
								}
								else {
									if( KonfiguracjaUst[i].opcje != 0 ){
										for (ii=0; ii<UstawieniaWarIlosc; ii++)
										{
											if( KonfiguracjaUst[i].opcje == KonfiguracjaWar[ii].opcje ){
												if( strcmp((char*)DaneWar, KonfiguracjaWar[ii].nazwa)==0 ){
													ok=2;
													break;
												}
											}
										}
									}
									else {
										ok=2;
									}
								}

								if( ok == 1 ){
									if( KonfiguracjaUst[i].opcje == 0 ){
										if((KonfiguracjaUst[i].min <= inDataWar) && (KonfiguracjaUst[i].max >= inDataWar)){
											ZmienOpcje(KonfiguracjaUst[i].nazwaopcji, inDataWar);
											break;
										}
										else {
											konfigError = TRUE;
											break;
										}
									}
									else {
										ZmienOpcje(KonfiguracjaUst[i].nazwaopcji, inDataWar);
										break;
									}
								}
								else if( ok == 2 ){
									//PobierzKodKreskowy(KonfiguracjaUst[i].byte, KonfiguracjaUst[i].bit, KonfiguracjaWar[ii].wartosc);
									ZmienKodKreskowy(KonfiguracjaUst[i].byte, KonfiguracjaUst[i].bit, KonfiguracjaWar[ii].wartosc);
									break;
								}
								else if( ok == 3 ){
									if(strcmp("DOK_WY_RODZAJ_DOKUMENTU",DaneNaz)==0) {
										ZmienOpcje((char*)"DokumentWyDokumentIdRodzaj.", inDataWar);
									}
									if(strcmp("D£UGOŒÆ_OPIS_DOK/PO",DaneNaz)==0) {
										//ZmienOpcje((char*)"BazaDokumentOpisDok.", inDataWar);
										ZmienOpcje((char*)"BazaDokumentOpisPoz.", inDataWar);
									}
									if(strcmp("DOK_WY_OPIS_DOK/POZ",DaneNaz)==0) {
										ZmienOpcje((char*)"DokumentWyDokumentOpisPoz.", inDataWar);
									}
									if(strcmp("DOK_WE_OPIS_DOK/POZ",DaneNaz)==0) {
										ZmienOpcje((char*)"DokumentWeDokumentOpisPoz.", inDataWar);
									}
									if(strcmp("KONTRAHENT_ID_KONTRAHENTA", DaneNaz)==0) {
										ZmienOpcje((char*)"DokumentWeKontrahentId.", inDataWar);
									}
									if(strcmp("KONTRAHENT_OPIS_KONTRAHENTA", DaneNaz)==0) {
										ZmienOpcje((char*)"DokumentWeKontrahentOpis.", inDataWar);
									}
									if(strcmp("DOK_WY_NR_DOKUMENTU", DaneNaz)==0) {
										ZmienOpcje((char*)"DokumentWyDokumentId.", inDataWar);
									}
									if(strcmp("OPIS_DO_DOKUMENTU", DaneNaz)==0) {
										//nie rób nic
									}
									if(strcmp("OPIS_DO_POZYCJI", DaneNaz)==0) {
										//nie rób nic
									}
									if(strcmp("KONTRAHENT_Z_POZA", DaneNaz)==0) {
										ZmienOpcje((char*)"DokumentKontrahentPoza.", inDataWar);
									}
									if(strcmp("RODZAJ_DOKUMENTU_Z_POZA", DaneNaz)==0) {
										ZmienOpcje((char*)"DokumentRodzajDokumentuPoza.", inDataWar);
									}
									break;
								}
								else {
									konfigError = TRUE;
									break;
								} 
							}
						}
					}

					if(ok==0){
						konfigError = TRUE;
					}
				}
				else if (strcmp("#INFO", tmp_plik) == 0){
				}
				else if ( (strcmp("#TOWARY", tmp_plik) == 0) || (strcmp("#KONTRAHENCI", tmp_plik) == 0) || (strcmp("#RODZAJE_DOK", tmp_plik) == 0) || (strcmp("#KOMPLETACJA", tmp_plik) == 0) ){
					int nFile = 0;
					if (strcmp("#TOWARY", tmp_plik) == 0){
						nFile = TOWARY;
						FileHandle = uPlik[TOWARY];
						oDemoLiczT++;
					}
					if (strcmp("#KONTRAHENCI", tmp_plik) == 0){
						nFile = KONTRAHENCI;
						FileHandle = uPlik[KONTRAHENCI];
						oDemoLiczK++;
					}
					if (strcmp("#RODZAJE_DOK", tmp_plik) == 0){
						nFile = RODZAJEDOK;
						FileHandle = uPlik[RODZAJEDOK];
						oDemoLiczR++;
					}
					if (strcmp("#KOMPLETACJA", tmp_plik) == 0){
						nFile = KOMPLETACJA;
						FileHandle = uPlik[KOMPLETACJA];
						oDemoLiczKm++;
					}

					if (Konfiguracja.Licencja == LIC_DEMO) {
						if((oDemoLiczT > Konfiguracja.Demo) && (nFile == TOWARY)) continue;
						if((oDemoLiczR > Konfiguracja.Demo) && (nFile == RODZAJEDOK)) continue;
						if((oDemoLiczK > Konfiguracja.Demo) && (nFile == KONTRAHENCI)) continue;
						if((oDemoLiczKm > Konfiguracja.Demo) && (nFile == KOMPLETACJA)) continue;
					}

					// konwersja struktury
					if(nFile == TOWARY) KonwersjaImportTowary();
					if(nFile == KONTRAHENCI) KonwersjaImportKontrahenci();
					if(nFile == RODZAJEDOK) KonwersjaImportRodzajeDok();
					if(nFile == KOMPLETACJA) KonwersjaImportKompletacja();

					// wyrównywanie pól
					if (d = Plik[nFile].Separator)        // lookup field properties : use delimiter
					{
						int  n, nLmt=(i-2);

						j = k = n = 0;

						for (i=0; i<strlen ((char *)Dane); i++)
						{
							c = Dane [i];
							if (c == d)                                // delimiter
							{
								while (n++ < Plik [nFile].DlugoscRekordu [j])
									Bufor [k++] = ' ';              // add space

								if (++j < Plik [nFile].PolaIlosc)
									Bufor [k++] = d;                // add delimiter

								n = 0;
							}
							else
							{
								if (n < Plik [nFile].DlugoscRekordu [j])
								{
									Bufor [k++] = c;                // add data
									n++;
								}
							}
						}

						while (n < Plik [nFile].DlugoscRekordu [j])
						{
							Bufor [k++] = ' ';                      // add space
							n++;
						}

						Bufor [k] = '\0';
						strcpy ((char *)Dane, (char *)Bufor);
					}

					if (free_memory () <= (long)strlen((char*)Dane)) 
					{
						return -10;
					}

					if(!add_member(FileHandle, (char *)Dane))
					{
						return -11;
					}
				}
				else {
					konfigError = TRUE;
				}
			}

			if( konfigError == TRUE )
			{
				break;
			}
		}

		if (free_memory () <= (long)strlen((char*)Dane)) 
		{
			return -10;
		}
	}//while

Koniec:
	close(tmp_file_handle);
	remove(TMP_FILE_NAME);

	errKonfig = KonfiguracjaTest();

	if(errKonfig!=0){

		WyswietlBladKonfiguracji(errKonfig);

		for (i=0; i<23; i++)
				ScannerDesTbl [i] = ustCzytnik[i];
	}
	else
	{
		if( konfigError == FALSE )
		{
			ZapiszKonfiguracje();

			if( (ustawienia > 0)  && (plikiImport == 0) )
			{
				WczytajKonfiguracje();

				StworzIndeks((int)TOWARY);
				StworzIndeks((int)DOKUMENTY);
				StworzIndeks((int)KONTRAHENCI);
				StworzIndeks((int)RODZAJEDOK);
				StworzIndeks((int)KOMPLETACJA);
			}
		}
		else
		{
			for (i=0; i<23; i++)
					ScannerDesTbl [i] = ustCzytnik[i];
		}
	}

	WczytajKonfiguracje();

	Czysc();

	on_beeper(dzwiek2);
	if( konfigError == FALSE )
		if(errKonfig != 0)
			Komunikat(72,0);
		else
			Komunikat(10,0);
	else
		Komunikat(18, linia);

	return 1;
}
