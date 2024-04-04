
# Documentație Sistem de Management al Evenimentelor și Utilizatorilor

Sistem de e-ticketing cu functionalitati pentru a creea infrastructura unui MVP de tipul aplicatiei noastre.

## Acțiuni și Interogări Disponibile

1. **Crearea unui eveniment** - Permite utilizatorilor să adauge un nou eveniment în sistem.
2. **Anularea unui eveniment** - Oferă posibilitatea de a anula un eveniment existent.
3. **Setarea capacitatii maxime (Tickets Sold Out)** - Marchează un eveniment ca având toate biletele vândute.
4. **Crearea unui utilizator** - Adaugă un nou utilizator în sistem.
5. **Ștergerea unui utilizator** - Permite eliminarea unui utilizator din sistem.
6. **Asignarea unui bilet către un utilizator** - Utilizatorii pot achiziționa bilete la evenimente.
7. **Upgrade utilizator** - Trecerea unui utilizator de la statutul normal la cel premium.
8. **Transferul unui bilet între utilizatori** - Permite utilizatorilor să transfere bilete între ei.
9. **Refund** - Utilizatorii pot returna biletele și primi o rambursare.
10. **Feedback pentru evenimente** - Utilizatorii pot oferi feedback pentru evenimentele la care au participat.
11. **Schimbarea datei evenimentului** - Organizatorii pot modifica data desfășurării evenimentelor.

## Tipuri de Obiecte

1. **User** - Reprezintă o persoană care utilizează sistemul.
   - **User Premium** - Utilizatori care plătesc un abonament lunar și beneficiază de discounturi.
   - **User Simplu** - Utilizatori standard, fără beneficii suplimentare.
2. **Ticket** - Bilet pentru participarea la un eveniment.
   - **Ticket VIP** - Oferă acces complet și beneficii suplimentare.
   - **Ticket Gold** - Include avantaje specifice, cum ar fi locuri mai bune.
   - **Ticket Silver** - Acces standard la evenimente.
3. **Eveniment** - Activitate planificată la care participanții se pot înscrie.
   - **Eveniment Muzical** - Concerte și festivaluri.
   - **Eveniment Automobilistic** - Curse auto și expoziții de mașini.
   - **Eveniment Aviatic** - Show-uri aeriene și expoziții de avioane.
   - **Eveniment Special** - Evenimente unice, cum ar fi lansări de produse sau conferințe.
