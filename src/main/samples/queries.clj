(ns samples.queries)
[:db/id {:notes/note-items
         [:db/id :note/url :note/title :note/description :note/type :note/image-url :note/domain]}]

[:db/id :kind :summary/by-id :summary/name]