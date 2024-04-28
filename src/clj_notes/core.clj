(ns clj-notes.core
  (:require [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(def ^:private notes (atom {}))

;; A few thoughts about what a clojure notebook might look like

(defn note
  "Fn for defining a note. Simply a map of meta, plus a string of the actual note."
  [opts note-str]
  {:pre [(map? opts)]}
  {:opts opts
   :note note-str})

;; This could be a terse form, but I don't think very readable from the standpoint that
;; foo does not really have any meaning.
(def foo ^"bar" "note text")

(defn -main [& args] (println "hello world"))

(comment
  ;; First thoughts...
  ;; This has some appeal. A note is just a map of text with metadata.
  ;; The metadata is inline with the note text. Could have optional forms
  ;; of this. The note fn could accept an option in-line note or the meta
  ;; could refer to other file(s).
  (note
   {:name :something-I-was-thinking-about
    :tags [:infra :foo-bar :baz]}
   "This is an example note. How practical is this? Should a def or defn be used
    to define a note in a namespace? Furthermore, the meta could be inlined in the var when using def
    or defn.
    ")

  ;; Other Thoughts
  ;; A disadvantage of having the text inline in a fn is that if you want to use markdown, or some other
  ;; common format, it will not be colored/formatted properly in a traditional clojure aware
  ;; editor. A simple way around this is to store the text in a separate markdown file.
  ;; The note fn then defines the meta info for a note. A common fn could scan all namespaces for
  ;; note defs and perform operations on the notes.
  ;; 
  ;; * Notes can be organized via namespaces.
  ;; * The text of a note could be in a separate markdown file as a resource. A note fn could
  ;;   provide meta referring to the resource file.
  ;; * A notebook is simply a git fork of this repository.
  ;; * There could be several commands that can be invoked in the main fn
  ;; * ns meta indicating the namespace contains notes. This will ID namespaces
  ;;   for tooling to process.
  ;; * A notebook can be built into an executable uber jar
  ;; * The note fn could store data into a DB (in-memory or file-system)
  ;;   Initial impl will probably not include a server component hosting a DB. In memory H2?
  ;; * H2's full text search might be useful.
  ;; * After writing down these thoughts, I realized that 
  ;;   [clerk](https://github.com/nextjournal/clerk) does much of this. However, I want to 
  ;;   continue with this approach for something simpler, with less dependencies.
  ;; 
  ;; Desired Operations
  ;; * Search
  ;; * Sorting and group by
  ;; * Table of contents?
  ;; * transforms to other formats
  ;; * Graphing of data?
  ;; * 

  (bean (first (all-ns)))

  ;;
  )