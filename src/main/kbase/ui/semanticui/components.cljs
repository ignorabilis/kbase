(ns kbase.ui.semanticui.components
  (:require
    [fulcro.client.primitives :as prim :refer [defsc]]
    [fulcro.client.dom :as dom]
    ["semantic-ui-react"
     :refer
     [Accordion
      AccordionAccordion
      AccordionContent
      AccordionTitle
      Advertisement
      Breadcrumb
      BreadcrumbDivider
      BreadcrumbSection
      Button
      ButtonContent
      ButtonGroup
      ButtonOr
      Card
      CardContent
      CardDescription
      CardGroup
      CardHeader
      CardMeta
      Checkbox
      Comment
      CommentAction
      CommentActions
      CommentAuthor
      CommentAvatar
      CommentContent
      CommentGroup
      CommentMetadata
      CommentText
      Confirm
      Container
      Dimmer
      DimmerDimmable
      Divider
      Dropdown
      DropdownDivider
      DropdownHeader
      DropdownItem
      DropdownMenu
      DropdownSearchInput
      Embed
      Feed
      FeedContent
      FeedDate
      FeedEvent
      FeedExtra
      FeedLabel
      FeedLike
      FeedMeta
      FeedSummary
      FeedUser
      Flag
      Form
      FormButton
      FormCheckbox
      FormDropdown
      FormField
      FormGroup
      FormInput
      FormRadio
      FormSelect
      FormTextArea
      Grid
      GridColumn
      GridRow
      Header
      HeaderContent
      HeaderSubheader
      Icon
      IconGroup
      Image
      ImageGroup
      Input
      Item
      ItemContent
      ItemDescription
      ItemExtra
      ItemGroup
      ItemHeader
      ItemImage
      ItemMeta
      Label
      LabelDetail
      LabelGroup
      List
      ListContent
      ListDescription
      ListHeader
      ListIcon
      ListItem
      ListList
      Loader
      Menu
      MenuHeader
      MenuItem
      MenuMenu
      Message
      MessageContent
      MessageHeader
      MessageItem
      MessageList
      Modal
      ModalActions
      ModalContent
      ModalDescription
      ModalHeader
      Popup
      PopupContent
      PopupHeader
      Portal
      Progress
      Radio
      Rail
      Rating
      RatingIcon
      Responsive
      Reveal
      RevealContent
      Search
      SearchCategory
      SearchResult
      SearchResults
      Segment
      SegmentGroup
      Select
      Sidebar
      SidebarPushable
      SidebarPusher
      Statistic
      StatisticGroup
      StatisticLabel
      StatisticValue
      Step
      StepContent
      StepDescription
      StepGroup
      StepTitle
      Sticky
      Tab
      TabPane
      Table
      TableBody
      TableCell
      TableFooter
      TableHeader
      TableHeaderCell
      TableRow
      TextArea
      Transition
      TransitionGroup
      TransitionablePortal
      Visibility]]))

(defn ui-accordion [& args] (dom/macro-create-element Accordion args))
(defn ui-accordion-accordion [& args] (dom/macro-create-element AccordionAccordion args))
(defn ui-accordion-content [& args] (dom/macro-create-element AccordionContent args))
(defn ui-accordion-title [& args] (dom/macro-create-element AccordionTitle args))
(defn ui-advertisement [& args] (dom/macro-create-element Advertisement args))
(defn ui-breadcrumb [& args] (dom/macro-create-element Breadcrumb args))
(defn ui-breadcrumb-divider [& args] (dom/macro-create-element BreadcrumbDivider args))
(defn ui-breadcrumb-section [& args] (dom/macro-create-element BreadcrumbSection args))
(defn ui-button [& args] (dom/macro-create-element Button args))
(defn ui-button-content [& args] (dom/macro-create-element ButtonContent args))
(defn ui-button-group [& args] (dom/macro-create-element ButtonGroup args))
(defn ui-button-or [& args] (dom/macro-create-element ButtonOr args))
(defn ui-card [& args] (dom/macro-create-element Card args))
(defn ui-card-content [& args] (dom/macro-create-element CardContent args))
(defn ui-card-description [& args] (dom/macro-create-element CardDescription args))
(defn ui-card-group [& args] (dom/macro-create-element CardGroup args))
(defn ui-card-header [& args] (dom/macro-create-element CardHeader args))
(defn ui-card-meta [& args] (dom/macro-create-element CardMeta args))
(defn ui-checkbox [& args] (dom/macro-create-element Checkbox args))
(defn ui-comment [& args] (dom/macro-create-element Comment args))
(defn ui-comment-action [& args] (dom/macro-create-element CommentAction args))
(defn ui-comment-actions [& args] (dom/macro-create-element CommentActions args))
(defn ui-comment-author [& args] (dom/macro-create-element CommentAuthor args))
(defn ui-comment-avatar [& args] (dom/macro-create-element CommentAvatar args))
(defn ui-comment-content [& args] (dom/macro-create-element CommentContent args))
(defn ui-comment-group [& args] (dom/macro-create-element CommentGroup args))
(defn ui-comment-metadata [& args] (dom/macro-create-element CommentMetadata args))
(defn ui-comment-text [& args] (dom/macro-create-element CommentText args))
(defn ui-confirm [& args] (dom/macro-create-element Confirm args))
(defn ui-container [& args] (dom/macro-create-element Container args))
(defn ui-dimmer [& args] (dom/macro-create-element Dimmer args))
(defn ui-dimmer-dimmable [& args] (dom/macro-create-element DimmerDimmable args))
(defn ui-divider [& args] (dom/macro-create-element Divider args))
(defn ui-dropdown [& args] (dom/macro-create-element Dropdown args))
(defn ui-dropdown-divider [& args] (dom/macro-create-element DropdownDivider args))
(defn ui-dropdown-header [& args] (dom/macro-create-element DropdownHeader args))
(defn ui-dropdown-item [& args] (dom/macro-create-element DropdownItem args))
(defn ui-dropdown-menu [& args] (dom/macro-create-element DropdownMenu args))
(defn ui-dropdown-search-input [& args] (dom/macro-create-element DropdownSearchInput args))
(defn ui-embed [& args] (dom/macro-create-element Embed args))
(defn ui-feed [& args] (dom/macro-create-element Feed args))
(defn ui-feed-content [& args] (dom/macro-create-element FeedContent args))
(defn ui-feed-date [& args] (dom/macro-create-element FeedDate args))
(defn ui-feed-event [& args] (dom/macro-create-element FeedEvent args))
(defn ui-feed-extra [& args] (dom/macro-create-element FeedExtra args))
(defn ui-feed-label [& args] (dom/macro-create-element FeedLabel args))
(defn ui-feed-like [& args] (dom/macro-create-element FeedLike args))
(defn ui-feed-meta [& args] (dom/macro-create-element FeedMeta args))
(defn ui-feed-summary [& args] (dom/macro-create-element FeedSummary args))
(defn ui-feed-user [& args] (dom/macro-create-element FeedUser args))
(defn ui-flag [& args] (dom/macro-create-element Flag args))
(defn ui-form [& args] (dom/macro-create-element Form args))
(defn ui-form-button [& args] (dom/macro-create-element FormButton args))
(defn ui-form-checkbox [& args] (dom/macro-create-element FormCheckbox args))
(defn ui-form-dropdown [& args] (dom/macro-create-element FormDropdown args))
(defn ui-form-field [& args] (dom/macro-create-element FormField args))
(defn ui-form-group [& args] (dom/macro-create-element FormGroup args))
(defn ui-form-input [& args] (dom/macro-create-element FormInput args))
(defn ui-form-radio [& args] (dom/macro-create-element FormRadio args))
(defn ui-form-select [& args] (dom/macro-create-element FormSelect args))
(defn ui-form-textArea [& args] (dom/macro-create-element FormTextArea args))
(defn ui-grid [& args] (dom/macro-create-element Grid args))
(defn ui-grid-column [& args] (dom/macro-create-element GridColumn args))
(defn ui-grid-row [& args] (dom/macro-create-element GridRow args))
(defn ui-header [& args] (dom/macro-create-element Header args))
(defn ui-header-content [& args] (dom/macro-create-element HeaderContent args))
(defn ui-header-subheader [& args] (dom/macro-create-element HeaderSubheader args))
(defn ui-icon [& args] (dom/macro-create-element Icon args))
(defn ui-icon-group [& args] (dom/macro-create-element IconGroup args))
(defn ui-image [& args] (dom/macro-create-element Image args))
(defn ui-image-group [& args] (dom/macro-create-element ImageGroup args))
(defn ui-input [& args] (dom/macro-create-element Input args))
(defn ui-item [& args] (dom/macro-create-element Item args))
(defn ui-item-content [& args] (dom/macro-create-element ItemContent args))
(defn ui-item-description [& args] (dom/macro-create-element ItemDescription args))
(defn ui-item-extra [& args] (dom/macro-create-element ItemExtra args))
(defn ui-item-group [& args] (dom/macro-create-element ItemGroup args))
(defn ui-item-header [& args] (dom/macro-create-element ItemHeader args))
(defn ui-item-image [& args] (dom/macro-create-element ItemImage args))
(defn ui-item-meta [& args] (dom/macro-create-element ItemMeta args))
(defn ui-label [& args] (dom/macro-create-element Label args))
(defn ui-label-detail [& args] (dom/macro-create-element LabelDetail args))
(defn ui-label-group [& args] (dom/macro-create-element LabelGroup args))
(defn ui-list [& args] (dom/macro-create-element List args))
(defn ui-list-content [& args] (dom/macro-create-element ListContent args))
(defn ui-list-description [& args] (dom/macro-create-element ListDescription args))
(defn ui-list-header [& args] (dom/macro-create-element ListHeader args))
(defn ui-list-icon [& args] (dom/macro-create-element ListIcon args))
(defn ui-list-item [& args] (dom/macro-create-element ListItem args))
(defn ui-list-list [& args] (dom/macro-create-element ListList args))
(defn ui-loader [& args] (dom/macro-create-element Loader args))
(defn ui-menu [& args] (dom/macro-create-element Menu args))
(defn ui-menu-header [& args] (dom/macro-create-element MenuHeader args))
(defn ui-menu-item [& args] (dom/macro-create-element MenuItem args))
(defn ui-menu-menu [& args] (dom/macro-create-element MenuMenu args))
(defn ui-message [& args] (dom/macro-create-element Message args))
(defn ui-message-content [& args] (dom/macro-create-element MessageContent args))
(defn ui-message-header [& args] (dom/macro-create-element MessageHeader args))
(defn ui-message-item [& args] (dom/macro-create-element MessageItem args))
(defn ui-message-list [& args] (dom/macro-create-element MessageList args))
(defn ui-modal [& args] (dom/macro-create-element Modal args))
(defn ui-modal-actions [& args] (dom/macro-create-element ModalActions args))
(defn ui-modal-content [& args] (dom/macro-create-element ModalContent args))
(defn ui-modal-description [& args] (dom/macro-create-element ModalDescription args))
(defn ui-modal-header [& args] (dom/macro-create-element ModalHeader args))
(defn ui-popup [& args] (dom/macro-create-element Popup args))
(defn ui-popup-content [& args] (dom/macro-create-element PopupContent args))
(defn ui-popup-header [& args] (dom/macro-create-element PopupHeader args))
(defn ui-portal [& args] (dom/macro-create-element Portal args))
(defn ui-progress [& args] (dom/macro-create-element Progress args))
(defn ui-radio [& args] (dom/macro-create-element Radio args))
(defn ui-rail [& args] (dom/macro-create-element Rail args))
(defn ui-rating [& args] (dom/macro-create-element Rating args))
(defn ui-rating-icon [& args] (dom/macro-create-element RatingIcon args))
(defn ui-responsive [& args] (dom/macro-create-element Responsive args))
(defn ui-reveal [& args] (dom/macro-create-element Reveal args))
(defn ui-reveal-content [& args] (dom/macro-create-element RevealContent args))
(defn ui-search [& args] (dom/macro-create-element Search args))
(defn ui-search-category [& args] (dom/macro-create-element SearchCategory args))
(defn ui-search-result [& args] (dom/macro-create-element SearchResult args))
(defn ui-search-results [& args] (dom/macro-create-element SearchResults args))
(defn ui-segment [& args] (dom/macro-create-element Segment args))
(defn ui-segment-group [& args] (dom/macro-create-element SegmentGroup args))
(defn ui-select [& args] (dom/macro-create-element Select args))
(defn ui-sidebar [& args] (dom/macro-create-element Sidebar args))
(defn ui-sidebar-pushable [& args] (dom/macro-create-element SidebarPushable args))
(defn ui-sidebar-pusher [& args] (dom/macro-create-element SidebarPusher args))
(defn ui-statistic [& args] (dom/macro-create-element Statistic args))
(defn ui-statistic-group [& args] (dom/macro-create-element StatisticGroup args))
(defn ui-statistic-label [& args] (dom/macro-create-element StatisticLabel args))
(defn ui-statistic-value [& args] (dom/macro-create-element StatisticValue args))
(defn ui-step [& args] (dom/macro-create-element Step args))
(defn ui-step-content [& args] (dom/macro-create-element StepContent args))
(defn ui-step-description [& args] (dom/macro-create-element StepDescription args))
(defn ui-step-group [& args] (dom/macro-create-element StepGroup args))
(defn ui-step-title [& args] (dom/macro-create-element StepTitle args))
(defn ui-sticky [& args] (dom/macro-create-element Sticky args))
(defn ui-tab [& args] (dom/macro-create-element Tab args))
(defn ui-tab-pane [& args] (dom/macro-create-element TabPane args))
(defn ui-table [& args] (dom/macro-create-element Table args))
(defn ui-table-body [& args] (dom/macro-create-element TableBody args))
(defn ui-table-cell [& args] (dom/macro-create-element TableCell args))
(defn ui-table-footer [& args] (dom/macro-create-element TableFooter args))
(defn ui-table-header [& args] (dom/macro-create-element TableHeader args))
(defn ui-table-header-cell [& args] (dom/macro-create-element TableHeaderCell args))
(defn ui-table-row [& args] (dom/macro-create-element TableRow args))
(defn ui-text-area [& args] (dom/macro-create-element TextArea args))
(defn ui-transition [& args] (dom/macro-create-element Transition args))
(defn ui-transition-group [& args] (dom/macro-create-element TransitionGroup args))
(defn ui-transitionable-portal [& args] (dom/macro-create-element TransitionablePortal args))
(defn ui-visibility [& args] (dom/macro-create-element Visibility args))
